package it.univaq.sose.watched_film.business;

import it.univaq.sose.watched_film.model.Film;
import it.univaq.sose.watched_film.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FilmBusiness {
    private static final Logger logger = LoggerFactory.getLogger(FilmBusiness.class);

    @Autowired
    FilmRepository filmRepository;

    public FilmBusiness() {}

    public Film getFilmById(long id) {
        try {
            Optional<Film> optional = filmRepository.findById(id);
            return optional.orElse(null);
        } catch (Exception e){
            logger.debug(e.getMessage());
        }
        return null;
    }

    public ArrayList<Film> getAll() {
        return (ArrayList<Film>) this.filmRepository.findAll();
    }
}
