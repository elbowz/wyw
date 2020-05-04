package it.univaq.sose.film.business;

import feign.hystrix.FallbackFactory;
import it.univaq.sose.film.client.PersonServiceClient;
import it.univaq.sose.film.model.GetPeopleForFilm;
import it.univaq.sose.film.model.GetPeopleForFilmResponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class PersonFallback implements FallbackFactory<PersonServiceClient> {
    @Override
    public PersonServiceClient create(Throwable throwable) {
        return new PersonServiceClient() {
            @Override
            public CompletableFuture<GetPeopleForFilmResponse> getPeopleForFilm(GetPeopleForFilm getPeopleForFilm) {
                // When person-ws is down return empty list of people.
                return CompletableFuture.completedFuture(new GetPeopleForFilmResponse());
            }
        };
    }
}
