package it.univaq.sose.service;

import javax.ws.rs.Path;

import org.apache.cxf.ext.logging.Logging;
import org.springframework.stereotype.Service;
import io.swagger.annotations.Api;

@Logging
@Path("/sayHello2")
@Api("/sayHello2")
@Service
public class HelloServiceImpl2 implements HelloService {

    public String sayHello(String a) {
        return "Hello2 " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }

}
