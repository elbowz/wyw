package it.univaq.sose.watched_film.client;

import it.univaq.sose.watched_film.model.Film;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@FeignClient(url = "http://www.omdbapi.com/", name="omdb-client")
public interface OmdbServiceClient {
    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    Film getFilmById(@QueryParam("i") String i, @QueryParam("apiKey") String apiKey);
}
