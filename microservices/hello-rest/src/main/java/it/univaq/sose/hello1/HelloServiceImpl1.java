package it.univaq.sose.hello1;

import it.univaq.sose.HelloService;
import org.springframework.stereotype.Service;
import io.swagger.annotations.Api;

@Api("/sayHello")
@Service
public class HelloServiceImpl1 implements HelloService {

    public String sayHello(String a) {
        return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }

}
