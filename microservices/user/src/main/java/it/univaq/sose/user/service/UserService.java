package it.univaq.sose.user.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserService {

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String pong();
}
