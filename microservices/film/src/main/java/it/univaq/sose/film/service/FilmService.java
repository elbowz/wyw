package it.univaq.sose.film.service;

import it.univaq.sose.film.model.Film;
import it.univaq.sose.film.model.TakesPart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/film")
public interface FilmService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Film getFilmById(@PathParam("id") String id);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Film> getAllFilms();

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String ping();

    @GET
    @Path("/{id}/people")
    @Produces(MediaType.APPLICATION_JSON)
    List<TakesPart> getPeopleForFilm(@PathParam("id") String filmId);
}