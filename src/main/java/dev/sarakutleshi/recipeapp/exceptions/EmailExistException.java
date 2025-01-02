package dev.sarakutleshi.recipeapp.exceptions;


public class EmailExistException extends RuntimeException {
    public EmailExistException(String message) {
        super(message);
    }

    public EmailExistException() {
        super("Email already exists");
    }
}
