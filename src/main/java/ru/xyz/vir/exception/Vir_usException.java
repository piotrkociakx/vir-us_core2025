package ru.xyz.vir.exception;

public class Vir_usException extends RuntimeException {
    public Vir_usException(String message, String error) {
        super(message+":"+error);
    }
}
