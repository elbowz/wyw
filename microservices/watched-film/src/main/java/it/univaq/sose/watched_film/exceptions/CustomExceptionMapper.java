package it.univaq.sose.watched_film.exceptions;

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
public class CustomExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        logger.debug("GeneralExceptionMapper occurred.");
        if (e instanceof UserNotFoundException) {
            logger.debug("UserNotFoundException occurred.");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (e instanceof MalformedBodyException) {
            logger.debug("MalformedBodyException occurred.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            logger.debug("GeneralExceptionMapper occurred.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}





