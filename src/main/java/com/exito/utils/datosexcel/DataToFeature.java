package com.exito.utils.datosexcel;

import com.exito.exceptions.PropertiesDoesNotLoadException;
import com.exito.utils.constants.TipoClaseConstantes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataToFeature {
    private DataToFeature() {
        throw new IllegalStateException(TipoClaseConstantes.CLASE_UTILIDAD);
    }

    private static List<String> setExcelDataToFeature(File featureFile) throws IOException {
        List<String> fileData = new ArrayList<>();
        try (BufferedReader buffReader = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(new FileInputStream(featureFile)), StandardCharsets.UTF_8))) {
            String data;
            List<Map<String, String>> excelData;
            boolean foundHashTag = false;
            boolean featureData = false;
            boolean esUnRango = false;
            boolean esMultiple = false;
            boolean esRangoDefinido = false;

            while ((data = buffReader.readLine()) != null) {
                String[] dataVector;
                String[] dataVectorRango = null;
                String sheetName = null;
                String excelFilePath = null;
                int filaSeleccionada = 0;
                int pos = 0;

                if (data.trim().contains("##@externaldata")) {
                    dataVector = data.trim().split("@");
                    excelFilePath = dataVector[2];
                    sheetName = dataVector[3];

                    if (dataVector.length == 4) {
                        esUnRango = true;
                    }

                    if (dataVector.length == 5) {
                        if (dataVector[4].contains("-")) {
                            dataVectorRango = dataVector[4].trim().split("-");
                            esRangoDefinido = true;
                            filaSeleccionada = Integer.parseInt(dataVectorRango[pos]) - 1;
                        } else if (dataVector[4].contains(",")) {
                            dataVectorRango = dataVector[4].trim().split(",");
                            esUnRango = true;
                            esMultiple = true;
                            filaSeleccionada = Integer.parseInt(dataVectorRango[pos]) - 1;
                        } else {
                            filaSeleccionada = Integer.parseInt(dataVector[4]) - 1;
                        }
                    }

                    foundHashTag = true;
                    fileData.add(data);
                }

                if (foundHashTag) {
                    try {
                        excelData = new LectorExcel().getData(excelFilePath, sheetName);
                    } catch (Exception e) {
                        throw new PropertiesDoesNotLoadException(e);
                    }

                    for (int rowNumber = filaSeleccionada; rowNumber < excelData.size() - 1; rowNumber++) {
                        StringBuilder allCellData = new StringBuilder();
                        String cellData;
                        for (Map.Entry<String, String> mapData : excelData.get(rowNumber).entrySet()) {
                            if (dataVectorRango == null) {
                                allCellData.append("   |").append(mapData.getValue());
                            } else {
                                if (esRangoDefinido) {
                                    if (rowNumber < Integer.parseInt(dataVectorRango[1])) {
                                        allCellData.append("   |").append(mapData.getValue());
                                    }
                                } else {
                                    if (rowNumber + 1 == Integer.parseInt(dataVectorRango[pos]) && esUnRango) {
                                        allCellData.append("   |").append(mapData.getValue());
                                    }

                                }
                            }
                        }
                        cellData = allCellData.toString();
                        fileData.add(cellData + "|");
                        if (!esUnRango && !esRangoDefinido) {
                            rowNumber = excelData.size();
                        }
                        if (esMultiple) {
                            assert dataVectorRango != null;
                            if (pos + 1 < dataVectorRango.length) {
                                filaSeleccionada = Integer.parseInt(dataVectorRango[pos + 1]) - 1;
                                rowNumber = filaSeleccionada - 1;
                                pos++;
                            } else {
                                rowNumber = excelData.size() - 1;
                            }

                        }
                        if (esRangoDefinido) {
                            assert dataVectorRango != null;
                            if (rowNumber + 1 == Integer.parseInt(dataVectorRango[1])) {
                                rowNumber = excelData.size() - 1;
                            }
                            pos++;

                        }

                    }
                    foundHashTag = false;
                    featureData = true;
                    continue;
                }

                if (data.startsWith("|") || data.endsWith("|")) {
                    if (!featureData) {
                        fileData.add(data);
                    }
                    continue;
                } else {
                    featureData = false;
                }
                fileData.add(data);
            }
        }
        return fileData;
    }

    private static List<File> listOfFeatureFiles(File folder) {
        List<File> featureFiles = new ArrayList<>();
        if (folder.getName().endsWith(".feature")) {
            featureFiles.add(folder);
        } else {

            for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    featureFiles.addAll(listOfFeatureFiles(fileEntry));
                } else {
                    if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
                        featureFiles.add(fileEntry);
                    }
                }
            }
        }
        return featureFiles;
    }

    public static void overrideFeatureFiles(String featuresDirectoryPath) throws IOException {
        List<File> listOfFeatureFiles = listOfFeatureFiles(new File(featuresDirectoryPath));
        for (File featureFile : listOfFeatureFiles) {
            List<String> featureWithExcelData = setExcelDataToFeature(featureFile);
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(featureFile), StandardCharsets.UTF_8))) {
                for (String string : featureWithExcelData) {
                    writer.write(string);
                    writer.write("\n");
                }
            }

        }
    }
}
