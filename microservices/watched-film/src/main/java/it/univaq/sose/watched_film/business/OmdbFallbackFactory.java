package it.univaq.sose.watched_film.business;


import feign.hystrix.FallbackFactory;
import it.univaq.sose.watched_film.client.FilmServiceClient;
import it.univaq.sose.watched_film.client.OmdbServiceClient;
import it.univaq.sose.watched_film.model.Film;
import it.univaq.sose.watched_film.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class OmdbFallbackFactory implements FallbackFactory<OmdbServiceClient> {

    @Autowired
    OmdbServiceClient omdbServiceClient;

    @Override
    public OmdbServiceClient create(Throwable throwable) {
        return new OmdbServiceClient() {

            @Override
            public CompletableFuture<Film> getFilmById(String i, String apiKey) {
                // System.out.println("Fallback called for: " + throwable.getLocalizedMessage());

                return CompletableFuture.completedFuture(new Film());
            }

            @Override
            public CompletableFuture<Ratings> getRatingsByFilmId(String i, String apiKey) {
                // System.out.println("Fallback called for: " + throwable.getLocalizedMessage());

                return CompletableFuture.completedFuture(new Ratings());
            }
        };
    }
}