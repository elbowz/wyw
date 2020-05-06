#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.model.${camelCaseArtifactId};
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/${artifactId}")
public interface ${camelCaseArtifactId}Service {
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String pong();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<${camelCaseArtifactId}> getAll${camelCaseArtifactId}s();

}