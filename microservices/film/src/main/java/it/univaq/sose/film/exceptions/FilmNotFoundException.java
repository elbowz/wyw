package it.univaq.sose.film.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException() {
        super("Requested film does not exist!");
    }
}
