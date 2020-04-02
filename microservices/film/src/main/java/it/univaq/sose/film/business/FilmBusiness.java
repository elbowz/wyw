package it.univaq.sose.film.business;

import it.univaq.sose.film.exceptions.FilmNotFoundException;
import it.univaq.sose.film.model.Film;
import it.univaq.sose.film.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FilmBusiness {

    @Autowired
    FilmRepository filmRepository;

    public FilmBusiness() {}

    public Film one(String id) {
        Optional<Film> optional = filmRepository.findByImdbID(id);
        return optional.orElseThrow(FilmNotFoundException::new);
    }

    public ArrayList<Film> getAll() {
        return (ArrayList<Film>) this.filmRepository.findAll();
    }
}
