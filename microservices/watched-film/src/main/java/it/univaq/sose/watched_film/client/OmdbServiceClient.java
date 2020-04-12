package it.univaq.sose.watched_film.client;

import it.univaq.sose.watched_film.business.OmdbFallbackFactory;
import it.univaq.sose.watched_film.model.Film;
import it.univaq.sose.watched_film.model.Ratings;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;


@FeignClient(url = "http://www.omdbapi.com/", name="omdb-client", fallbackFactory = OmdbFallbackFactory.class)
public interface OmdbServiceClient {
    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    CompletableFuture<Film> getFilmById(@QueryParam("i") String i, @QueryParam("apiKey") String apiKey);

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    CompletableFuture<Ratings> getRatingsByFilmId(@QueryParam("i") String i, @QueryParam("apiKey") String apiKey);
}