package it.univaq.sose.watched_film.business;

import it.univaq.sose.watched_film.client.FilmServiceClient;
import it.univaq.sose.watched_film.client.OmdbServiceClient;
import it.univaq.sose.watched_film.client.UserServiceClient;
import it.univaq.sose.watched_film.exceptions.UserNotFoundException;
import it.univaq.sose.watched_film.model.Watched;
import it.univaq.sose.watched_film.repository.WatchedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WatchedBusiness {
    static final Logger logger = LoggerFactory.getLogger(WatchedBusiness.class);

    @Autowired
    WatchedRepository watchedRepository;

    @Autowired
    FilmServiceClient filmServiceClient;

    @Autowired
    OmdbServiceClient omdbServiceClient;

    @Autowired
    UserServiceClient userServiceClient;

    public WatchedBusiness() {}

    public ArrayList<Watched> getWatchedFilmByUser(long id) {
        ArrayList<Watched> watchedFilm = (ArrayList<Watched>) watchedRepository.findWatchedByUserId(id);


        // If this user hasn't watched any film return 404.
        if (watchedFilm.size() == 0){
            throw new UserNotFoundException();
        }

        // Retrieve the information associated with the filmId of each film in the array.
//        for (Watched watched: watchedFilm){
//            watched.setFilm(filmServiceClient.getFilmById(watched.getFilmId()));
//        }
        String apiKey = System.getenv("OMDB_API_KEY");
        logger.debug("API_KEY: " + apiKey);
        for (Watched watched: watchedFilm){
            watched.setFilm(omdbServiceClient.getFilmById(watched.getFilmId(), apiKey));
        }

        // Retrieve the information associated with the userId of each watched in the array.
        for (Watched watched: watchedFilm){
            watched.setUser(userServiceClient.getUserById(watched.getUserId()));
        }

        return watchedFilm;
    }

    // TODO Maybe this should be removed.
    public ArrayList<Watched> getAll() {
        return (ArrayList<Watched>) this.watchedRepository.findAll();
    }
}
