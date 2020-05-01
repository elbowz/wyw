package it.univaq.sose.film.service;

import it.univaq.sose.film.model.Film;
import it.univaq.sose.film.model.TakesPart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/film")
public interface FilmService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Film getFilmById(@PathParam("id") String id, @QueryParam("deep") int withPeople);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Film> getAllFilms(@DefaultValue("") @QueryParam("query") String query);

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String ping();

    @GET
    @Path("/{id}/people")
    @Produces(MediaType.APPLICATION_JSON)
    List<TakesPart> getPeopleForFilm(@PathParam("id") String filmId);
}