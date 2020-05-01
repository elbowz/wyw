package it.univaq.sose.film.business;

import it.univaq.sose.film.client.PersonServiceClient;
import it.univaq.sose.film.exceptions.FilmNotFoundException;
import it.univaq.sose.film.model.Film;
import it.univaq.sose.film.model.GetPeopleForFilm;
import it.univaq.sose.film.model.GetPeopleForFilmResponse;
import it.univaq.sose.film.model.TakesPart;
import it.univaq.sose.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmBusiness {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    PersonServiceClient personServiceClient;

    public FilmBusiness() {}

    public Film one(String id) {
        Optional<Film> optional = filmRepository.findByImdbID(id);
        return optional.orElseThrow(FilmNotFoundException::new);
    }

    public ArrayList<Film> getAll() {
        return (ArrayList<Film>) this.filmRepository.findAll();
    }

    public ArrayList<Film> search(String query) {
        return (ArrayList<Film>) this.filmRepository.findByTitleIgnoreCaseContaining(query);
    }

    public List<TakesPart> getAllPeopleForFilm(String filmId) {
        GetPeopleForFilm getPeopleForFilm = new GetPeopleForFilm();
        getPeopleForFilm.setFilmId(filmId);

        GetPeopleForFilmResponse response = personServiceClient.getPeopleForFilm(getPeopleForFilm);

        return response.getReturn();
    }

    public Film oneWithPeople(String filmId, int deep) {
        Optional<Film> optional = filmRepository.findByImdbID(filmId);

        if (deep == 1) {
            optional.ifPresent(film -> film.setPeople(getAllPeopleForFilm(filmId)));
        }

        return optional.orElseThrow(FilmNotFoundException::new);
    }
}
