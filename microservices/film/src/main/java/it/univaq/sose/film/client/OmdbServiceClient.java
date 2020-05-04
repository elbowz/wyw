package it.univaq.sose.film.client;

import it.univaq.sose.film.RESTConfig;
import it.univaq.sose.film.business.OmdbFallbackFactory;

import it.univaq.sose.film.model.Film;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;


@FeignClient(url = "http://www.omdbapi.com/", name = "omdb-client", configuration = RESTConfig.class,
        fallbackFactory = OmdbFallbackFactory.class)
public interface OmdbServiceClient {
//    @GET
//    @Path("")
//    @Produces(MediaType.APPLICATION_JSON)
//    CompletableFuture<List<Rate>> getRatingsByFilmId(@QueryParam("i") String i, @QueryParam("apiKey") String apiKey);

    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    CompletableFuture<Film> getFilmById(@QueryParam("i") String i, @QueryParam("apiKey") String apiKey);

}