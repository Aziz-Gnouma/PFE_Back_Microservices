package net.codejava.api.exception;

public class FormationNotFoundException extends RuntimeException {

    public FormationNotFoundException(String message) {
        super(message);
    }

    public FormationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}