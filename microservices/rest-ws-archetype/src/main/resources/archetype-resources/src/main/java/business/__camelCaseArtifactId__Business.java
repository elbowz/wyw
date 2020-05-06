#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business;

import ${package}.model.${camelCaseArtifactId};
import ${package}.repository.${camelCaseArtifactId}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ${camelCaseArtifactId}Business {

    @Autowired
    ${camelCaseArtifactId}Repository ${artifactId}Repository;

    public ${camelCaseArtifactId}Business() {
    }

    public ArrayList<${camelCaseArtifactId}> getAll${camelCaseArtifactId}s() {
        return (ArrayList<${camelCaseArtifactId}>) this.${artifactId}Repository.findAll();
    }

}
