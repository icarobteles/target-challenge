package com.target.console.exceptions;

public class InvalidEntryException extends Exception {

    public InvalidEntryException() {
        super("Entrada inválida. Tente novamente!");
    }

    public InvalidEntryException(String message) {
        super(message);
    }
}
