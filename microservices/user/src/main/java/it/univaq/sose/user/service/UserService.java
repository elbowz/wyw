package it.univaq.sose.user.service;

import it.univaq.sose.user.exceptions.DuplicatedUserException;
import it.univaq.sose.user.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
public interface UserService {
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String pong();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<User> getAllUsers();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    User getUserById(@PathParam("id") Long id);

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    User insertUser(@Valid @RequestBody User user) throws DuplicatedUserException;

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    User updateUser(@Valid @RequestBody User user, @PathParam("id") long id);

}