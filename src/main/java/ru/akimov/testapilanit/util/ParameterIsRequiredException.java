package ru.akimov.testapilanit.util;

public class ParameterIsRequiredException extends RuntimeException{
    public ParameterIsRequiredException(String message) {
        super(message);
    }
}
