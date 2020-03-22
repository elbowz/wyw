package it.univaq.sose.user.service;

import it.univaq.sose.user.business.UserBusiness;
import it.univaq.sose.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
