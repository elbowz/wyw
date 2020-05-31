package it.univaq.sose.watched_film.business;

import it.univaq.sose.watched_film.client.FilmServiceClient;
import it.univaq.sose.watched_film.client.UserServiceClient;
import it.univaq.sose.watched_film.exceptions.MalformedBodyException;
import it.univaq.sose.watched_film.exceptions.UserNotFoundException;
import it.univaq.sose.watched_film.model.Film;
import it.univaq.sose.watched_film.model.User;
import it.univaq.sose.watched_film.model.Watched;
import it.univaq.sose.watched_film.repository.WatchedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
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
    EurekaInstanceConfigBean eurekaInstanceConfigBean;

    public WatchedBusiness() {
    }

    /**
     * Return list of film watched by a given user.
     *
     * @param userId
     * @param deep specify how much information we want. 2=all, 1=no person-ws, 0=just watched-film-ws.
     * @return
     */
    public ArrayList<Watched> getWatchedFilmByUser(long userId, int deep) {
        int withPeople = deep == 2 ? 1 : 0;

        int withFilmAndUser = deep >= 1 ? 1: 0;

        ArrayList<Watched> watchedFilm = (ArrayList<Watched>) watchedRepository.findWatchedByUserId(userId);

        // If this user hasn't watched any film return 404.
        if (watchedFilm.size() == 0) {
            throw new UserNotFoundException();
        } else {
            watchedFilm.forEach(watched -> watched.setInstanceId(this.eurekaInstanceConfigBean.getInstanceId()));
        }

        // If the requested deep is 0 just return the watched-film-db data.
        if (withFilmAndUser == 0){
            return watchedFilm;
        }

        // Used to contain all the completable futures and wait to respond until all of them are completed.
        LinkedList<CompletableFuture> linkedList = new LinkedList<>();

        // Get all films information.
        for (Watched watched : watchedFilm) {
            CompletableFuture c = filmServiceClient
                    .getFilmById(watched.getFilmId(), withPeople)
                    .thenAccept(watched::setFilm);
            linkedList.add(c);
        }

        // Get user information.
        long id = watchedFilm.get(0).getUserId();

        CompletableFuture c = userServiceClient
                .getUserById(id)
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
                .getFilmById(watched.getFilmId(), 0)
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
        if (newFilm.get() == null) {
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

    public boolean deleteById (Long id) {
        try {
            watchedRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
