package it.univaq.sose.watched_film.client;

import it.univaq.sose.watched_film.business.FilmFallbackFactory;
import it.univaq.sose.watched_film.model.Film;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;


@FeignClient(name = "film-ws", fallbackFactory = FilmFallbackFactory.class)
public interface FilmServiceClient {
    @GET()
    @Path("/film/{filmId}")
    @Produces(MediaType.APPLICATION_JSON)
    CompletableFuture<Film> getFilmById(@PathParam("filmId") String filmId, @QueryParam("deep") int withPeople);
}
