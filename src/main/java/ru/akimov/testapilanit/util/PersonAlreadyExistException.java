package ru.akimov.testapilanit.util;

public class PersonAlreadyExistException extends RuntimeException{
    public PersonAlreadyExistException(String message) {
        super(message);
    }
}
