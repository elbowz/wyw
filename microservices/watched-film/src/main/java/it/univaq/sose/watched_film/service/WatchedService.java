package it.univaq.sose.watched_film.service;

import io.swagger.annotations.ApiOperation;
import it.univaq.sose.watched_film.model.Watched;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/watched")
public interface WatchedService {
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Returns the list of watched film with information for the given user id.")
    List<Watched> getWatchedByUserId(@PathParam("userId") Long userId, @DefaultValue("2") @QueryParam("deep") int deep);

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String ping();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Watched addWatched(@Valid @RequestBody Watched watched);

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete the watched film.")
    void deleteWatchedById(@PathParam("id") Long id);
}