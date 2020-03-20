package it.univaq.sose.service;


import io.swagger.annotations.Api;
import it.univaq.sose.model.Film;
import it.univaq.sose.repository.FilmRepository;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

@Logging
@Service
@Api("/film")
@Path("/film")
public class HelloServiceJsonImpl implements HelloServiceJson
{
	@Autowired
	private FilmRepository filmRepository;

	@Override
	public Iterable<Film> ping() {
		return filmRepository.findAll();
	}
}
