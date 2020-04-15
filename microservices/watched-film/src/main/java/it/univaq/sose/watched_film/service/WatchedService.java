package it.univaq.sose.watched_film.service;

import it.univaq.sose.watched_film.model.Watched;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.*;
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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Watched addWatched(@Valid @RequestBody Watched watched);
}