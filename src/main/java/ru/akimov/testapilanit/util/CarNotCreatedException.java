package ru.akimov.testapilanit.util;

public class CarNotCreatedException extends RuntimeException{
    public CarNotCreatedException(String message) {
        super(message);
    }
}
