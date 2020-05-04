package it.univaq.sose.watched_film.business;


import feign.hystrix.FallbackFactory;
import it.univaq.sose.watched_film.client.FilmServiceClient;
import it.univaq.sose.watched_film.client.OmdbServiceClient;
import it.univaq.sose.watched_film.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class FilmFallbackFactory implements FallbackFactory<FilmServiceClient> {

    @Autowired
    OmdbServiceClient omdbServiceClient;

    @Override
    public FilmServiceClient create(Throwable throwable) {
        return new FilmServiceClient() {
            @Override
            public CompletableFuture<Film> getFilmById(String filmId, int withPeople) {
                System.out.println("Fallback called for: " + throwable.getLocalizedMessage());

                // If film-ws is down then use OMDB.
                String apiKey = System.getenv("OMDB_API_KEY");

                return omdbServiceClient.getFilmById(filmId, apiKey);
            }
        };
    }
}