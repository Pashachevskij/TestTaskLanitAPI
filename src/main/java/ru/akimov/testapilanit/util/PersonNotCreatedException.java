package ru.akimov.testapilanit.util;

public class PersonNotCreatedException extends RuntimeException{
    public PersonNotCreatedException(String message) {
        super(message);
    }
}
