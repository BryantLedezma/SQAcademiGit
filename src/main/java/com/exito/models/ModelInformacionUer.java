package com.exito.models;

import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelInformacionUer {
    static List<Map<String, String>> dataUser = new ArrayList<>();

    public ModelInformacionUer(DataTable dataTable) {
        dataUser = dataTable.asMaps(String.class, String.class);
    }

    public static String getProductos() {
        return dataUser.get(0).get("productos");
    }

}
