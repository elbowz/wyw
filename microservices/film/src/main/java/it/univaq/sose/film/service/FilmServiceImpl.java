package it.univaq.sose.film.service;


import io.swagger.annotations.Api;
import it.univaq.sose.film.business.FilmBusiness;
import it.univaq.sose.film.model.Film;
import it.univaq.sose.film.model.TakesPart;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Logging
@Service
@Api("/film")
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmBusiness filmBusiness;

    @Override
    public Film getFilmById(String id, int deep) {
        return filmBusiness.one(id, deep);
    }

    @Override
    public List<Film> getAllFilms(String query) {
        if(query.isEmpty()) {
            return filmBusiness.getAll();
        } else {
            return filmBusiness.search(query);
        }
    }

    @Override
    public String ping() {
        return "Pong";
    }

    @Override
    public List<TakesPart> getPeopleForFilm(String filmId) {
        return filmBusiness.getAllPeopleForFilm(filmId);
    }
}
