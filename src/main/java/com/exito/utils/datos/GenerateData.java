package com.exito.utils.datos;

import java.util.Random;


public class GenerateData {

    private GenerateData() {
    }
    public static int posicion() {
        Random random = new Random();
        return random.nextInt(20 + 5) + 5;
    }

}
