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
class CustomNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    private static final Logger logger = LoggerFactory.getLogger(CustomNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(UserNotFoundException e) {
        logger.debug("UserNotFoundException occurred.");
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

// TODO Check if we need this.
//@Provider
//@Produces(MediaType.APPLICATION_JSON)
//@Component
//class GeneralExceptionMapper implements ExceptionMapper<Exception> {
//    private static final Logger logger = LoggerFactory.getLogger(CustomNotFoundExceptionMapper.class);
//
//    @Override
//    public Response toResponse(Exception e) {
//        logger.debug("UserNotFoundException occurred.");
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//    }
//}


