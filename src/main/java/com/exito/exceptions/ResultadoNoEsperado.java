package com.exito.exceptions;

public class ResultadoNoEsperado extends AssertionError {
    public static final String ERROR_ADD_CART = "Error al agregar producto al carrito de compras";

    public ResultadoNoEsperado(String message, Throwable cause) {
        super(message, cause);
    }
}