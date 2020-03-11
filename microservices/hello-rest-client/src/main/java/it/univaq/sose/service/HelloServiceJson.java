package it.univaq.sose.service;

import it.univaq.sose.model.Hello;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;

@Path("/say")
@FeignClient(path="/sose/helloservice", name = "jaxrs-hello-world-service")
public interface HelloServiceJson
{
	@GET
	@Path("/hello")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Hello sayHello();
}
