package it.univaq.sose.film.client;

import feign.Headers;
import it.univaq.sose.film.Config;
import it.univaq.sose.film.business.PersonFallback;
import it.univaq.sose.film.model.GetPeopleForFilm;
import it.univaq.sose.film.model.GetPeopleForFilmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.core.MediaType;

@FeignClient(name = "person-ws", configuration = Config.class, fallbackFactory = PersonFallback.class)
public interface PersonServiceClient {
    @PostMapping(value = "/person", consumes = MediaType.TEXT_XML, produces = MediaType.TEXT_XML)
    @Headers({ "SOAPAction: getPeopleForFilm" })
    GetPeopleForFilmResponse getPeopleForFilm(@RequestBody GetPeopleForFilm getPeopleForFilm);
}