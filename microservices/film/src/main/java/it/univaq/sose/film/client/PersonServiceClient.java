package it.univaq.sose.film.client;

import feign.Headers;
import it.univaq.sose.film.SOAPConfig;
import it.univaq.sose.film.business.PersonFallback;
import it.univaq.sose.film.model.GetPeopleForFilm;
import it.univaq.sose.film.model.GetPeopleForFilmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;

@FeignClient(name = "person-ws", configuration = SOAPConfig.class, fallbackFactory = PersonFallback.class)
public interface PersonServiceClient {
    @POST()
    @Path("/person")
    @Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_XML)
    @Headers({ "SOAPAction: getPeopleForFilm" })
    CompletableFuture<GetPeopleForFilmResponse> getPeopleForFilm(@RequestBody GetPeopleForFilm getPeopleForFilm);
}