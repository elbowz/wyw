package it.univaq.sose.film.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
@Produces(MediaType.APPLICATION_JSON)
@Component
public class CustomNotFoundExceptionMapper implements ExceptionMapper<FilmNotFoundException> {
    private static final Logger logger = LoggerFactory.getLogger(FilmNotFoundException.class);

    @Override
    public Response toResponse(FilmNotFoundException e) {
        logger.debug("FilmNotFoundException occurred.");
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}