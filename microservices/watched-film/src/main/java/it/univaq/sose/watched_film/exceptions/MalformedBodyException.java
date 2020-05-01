package it.univaq.sose.watched_film.exceptions;

public class MalformedBodyException extends RuntimeException {
    public MalformedBodyException() {
        super("Provided body has wrong information!");
    }
}
