package it.univaq.sose.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Requested user does not exist!");
    }
}
