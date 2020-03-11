package it.univaq.sose.service;

import org.apache.cxf.ext.logging.Logging;
import org.springframework.stereotype.Service;
import io.swagger.annotations.Api;

@Logging
@Service
@Api("/sayHello")
public class HelloServiceImpl1 implements HelloService {

    public String sayHello(String a) {
        return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }

}
