package it.univaq.sose.service;


import io.swagger.annotations.Api;
import it.univaq.sose.model.Hello;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.stereotype.Service;

@Logging
@Service
@Api("/sayHelloJson")
public class HelloServiceJsonImpl implements HelloServiceJson
{
	@Override
	public Hello sayHello()
	{
		return new Hello("Ciao!");
	}
}
