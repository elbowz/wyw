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
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

@Service
public class WatchedBusiness {

    @Autowired
    WatchedRepository watchedRepository;

    @Autowired
    FilmServiceClient filmServiceClient;

    @Autowired
    UserServiceClient userServiceClient;

    @Autowired
    OmdbServiceClient omdbServiceClient;

    public WatchedBusiness() {
    }

    public ArrayList<Watched> getWatchedFilmByUser(long id) {
        ArrayList<Watched> watchedFilm = (ArrayList<Watched>) watchedRepository.findWatchedByUserId(id);

        // If this user hasn't watched any film return 404.
        if (watchedFilm.size() == 0) {
            throw new UserNotFoundException();
        }

        // Get score for each film.
        String apiKey = System.getenv("OMDB_API_KEY");

        // Used to contain all the completable futures and wait to respond until all of them are completed.
        LinkedList<CompletableFuture> linkedList = new LinkedList<>();

        // Get all films information.
        for (Watched watched : watchedFilm) {
            CompletableFuture c = filmServiceClient
                    .getFilmById(watched.getFilmId())
                    .thenCompose(film -> {
                        watched.setFilm(film);
                        return omdbServiceClient.getRatingsByFilmId(watched.getFilmId(), apiKey);
                    })
                    .thenAccept(ratings -> {
                        watched.getFilm().setRatings(ratings);
                    });
            linkedList.add(c);
        }

        // Get user information.
        long userId = watchedFilm.get(0).getUserId();

        CompletableFuture c = userServiceClient
                .getUserById(userId)
                .thenAccept(user -> watchedFilm.forEach(watched -> watched.setUser(user)));

        linkedList.add(c);

        linkedList.forEach(CompletableFuture::join);
        return watchedFilm;
    }

    // TODO Maybe this should be removed.
    public ArrayList<Watched> getAll() {
        return (ArrayList<Watched>) this.watchedRepository.findAll();
    }
}
