package it.univaq.sose.watched_film.service;

import it.univaq.sose.watched_film.model.Watched;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/watched")
public interface WatchedService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Watched> getWatchedById(@PathParam("id") Long id);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Watched> getAllWatched();

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String ping();
}