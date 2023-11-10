package ru.akimov.testapilanit.util;

public class ParameterCantBeStringException extends RuntimeException{
    public ParameterCantBeStringException(String message) {
        super(message);
    }
}
