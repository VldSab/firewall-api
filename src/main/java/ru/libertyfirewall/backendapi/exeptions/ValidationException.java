package ru.libertyfirewall.backendapi.exeptions;

public class ValidationException extends Exception {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
