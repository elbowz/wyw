package it.univaq.sose.watched_film.business;

import it.univaq.sose.watched_film.client.FilmServiceClient;
import it.univaq.sose.watched_film.client.OmdbServiceClient;
import it.univaq.sose.watched_film.client.UserServiceClient;
import it.univaq.sose.watched_film.exceptions.MalformedBodyException;
import it.univaq.sose.watched_film.exceptions.UserNotFoundException;
import it.univaq.sose.watched_film.model.Film;
import it.univaq.sose.watched_film.model.User;
import it.univaq.sose.watched_film.model.Watched;
import it.univaq.sose.watched_film.repository.WatchedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

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

    public Watched save(Watched watched) {
        AtomicReference<Film> newFilm = new AtomicReference<>();
        AtomicReference<User> newUser = new AtomicReference<>();

        // Used to contain all the completable futures and wait to respond until all of them are completed.
        LinkedList<CompletableFuture> linkedList = new LinkedList<>();

        // Get film with this id (note: fallback will be used during the call).
        CompletableFuture c = filmServiceClient
                .getFilmById(watched.getFilmId())
                .thenAccept(newFilm::set);

        linkedList.add(c);

        // Get user with this id (note: fallback will be used during the call).
        CompletableFuture c1 = userServiceClient
                .getUserById(watched.getUserId())
                .thenAccept(newUser::set);

        linkedList.add(c1);

        // Wait for resolution.
        linkedList.forEach(CompletableFuture::join);

        // Check if film is valid.
        if (newFilm.get().getTitle() == null) {
            throw new MalformedBodyException();
        }

        // Check if user is valid.
        if (newUser.get() ==  null) {
            throw new MalformedBodyException();
        }

        // Check for duplicates.
        if (watchedRepository.hasUserWatchedFilm(watched.getUserId(), watched.getFilmId())) {
            throw new MalformedBodyException();
        }

        return watchedRepository.save(watched);
    }
}
