package it.univaq.sose.watched_film.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Requested user does not exist!");
    }
}
