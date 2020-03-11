package it.univaq.sose.service;

import io.swagger.annotations.Api;
import it.univaq.sose.model.Hello;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Logging
@Service
@Path("/proxy")
@Api("/proxy")
public class ProxyServiceImpl
{
	@Autowired
	private HelloServiceJson helloServiceJson;

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Hello proxyHello()
	{
		return helloServiceJson.sayHello();
	}
}
