package it.univaq.sose.watched_film.service;

import it.univaq.sose.watched_film.model.Film;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/watched")
public interface FilmService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Film getFilmById(@PathParam("id") Long id);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Film> getAllFilms();

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String ping();
}