package ru.akimov.testapilanit.util;

public class CarAlreadyExistException extends RuntimeException{
    public CarAlreadyExistException(String message) {
        super(message);
    }
}
