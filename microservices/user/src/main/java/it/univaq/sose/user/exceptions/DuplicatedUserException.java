package it.univaq.sose.user.exceptions;

public class DuplicatedUserException extends RuntimeException {
    public DuplicatedUserException() {
        super("User already exists!");
    }
}
