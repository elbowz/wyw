package it.univaq.sose;

import javax.xml.ws.Endpoint;

import it.univaq.sose.service.PersonSOAPServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    PersonSOAPServiceImpl personSOAPService;

    @Bean
    public Endpoint endpoint() {
        // EndpointImpl endpoint = new EndpointImpl(bus, new HelloWorldImpl());
    EndpointImpl endpoint = new EndpointImpl(bus, personSOAPService);
        // endpoint.publish("/hello");
        endpoint.publish("/person");

        return endpoint;
    }
}
