#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.model.${camelCaseArtifactId};
import org.springframework.data.jpa.repository.JpaRepository;


public interface ${camelCaseArtifactId}Repository extends JpaRepository<${camelCaseArtifactId}, Long> {}
