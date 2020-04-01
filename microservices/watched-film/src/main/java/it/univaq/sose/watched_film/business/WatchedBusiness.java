package it.univaq.sose.watched_film.business;

import it.univaq.sose.watched_film.client.FilmServiceClient;
import it.univaq.sose.watched_film.exceptions.UserNotFoundException;
import it.univaq.sose.watched_film.model.Watched;
import it.univaq.sose.watched_film.repository.WatchedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WatchedBusiness {

    @Autowired
    WatchedRepository watchedRepository;

    @Autowired
    FilmServiceClient filmServiceClient;

    public WatchedBusiness() {}

    public ArrayList<Watched> getWatchedFilmByUser(long id) {
        ArrayList<Watched> watchedFilm = (ArrayList<Watched>) watchedRepository.findWatchedByUserId(id);


        // If this user hasn't watched any film return 404.
        if (watchedFilm.size() == 0){
            throw new UserNotFoundException();
        }

        // Retrieve the information associated with the filmId of each film in the array.
        for (Watched watched: watchedFilm){
            watched.setFilm(filmServiceClient.getFilmById(watched.getFilmId()));
        }

        return watchedFilm;
    }

    // TODO Maybe this should be removed.
    public ArrayList<Watched> getAll() {
        return (ArrayList<Watched>) this.watchedRepository.findAll();
    }
}
