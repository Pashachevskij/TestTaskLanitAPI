package ru.akimov.testapilanit.util;

public class OwnerNotAdultException extends RuntimeException{
    public OwnerNotAdultException(String message) {
        super(message);
    }
}
