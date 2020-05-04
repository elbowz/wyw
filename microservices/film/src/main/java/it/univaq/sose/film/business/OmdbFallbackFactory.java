package it.univaq.sose.film.business;

import feign.hystrix.FallbackFactory;
import it.univaq.sose.film.client.OmdbServiceClient;
import it.univaq.sose.film.model.Film;
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
                System.out.println("1 - Fallback called for: " + throwable.getLocalizedMessage());

                // When OMDB is down and film information can't be found return null.
                return CompletableFuture.completedFuture(null);
            }
        };
    }
}