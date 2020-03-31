package it.univaq.sose.watched_film.business;

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

    public WatchedBusiness() {}

    public ArrayList<Watched> getWatchedFilmByUser(long id) {
        ArrayList<Watched> watcheds = (ArrayList<Watched>) watchedRepository.findWatchedByUserId(id);

        if (watcheds.size() == 0){
            throw new UserNotFoundException();
        } else {
            return watcheds;
        }
    }

    // TODO Maybe this should be removed.
    public ArrayList<Watched> getAll() {
        return (ArrayList<Watched>) this.watchedRepository.findAll();
    }
}
