#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import io.swagger.annotations.Api;
import ${package}.business.${camelCaseArtifactId}Business;
import ${package}.model.${camelCaseArtifactId};
import org.apache.cxf.ext.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Logging
@Service
@Api("/${artifactId}")
public class ${camelCaseArtifactId}ServiceImpl implements ${camelCaseArtifactId}Service {

    @Autowired
    ${camelCaseArtifactId}Business ${artifactId}Business;

    @Override
    public String pong() {
        return "Pong";
    }

    @Override
    public List<${camelCaseArtifactId}> getAll${camelCaseArtifactId}s() {
        return ${artifactId}Business.getAll${camelCaseArtifactId}s();
    }
}
