package it.univaq.sose.watched_film.client;

import it.univaq.sose.watched_film.model.User;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@FeignClient(name = "user-ws")
public interface UserServiceClient {
    @GET()
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    User getUserById(@PathParam("userId") long userId);
}
