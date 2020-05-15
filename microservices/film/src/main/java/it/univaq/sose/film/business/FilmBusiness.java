package it.univaq.sose.film.business;

import it.univaq.sose.film.client.OmdbServiceClient;
import it.univaq.sose.film.client.PersonServiceClient;
import it.univaq.sose.film.exceptions.FilmNotFoundException;
import it.univaq.sose.film.model.*;
import it.univaq.sose.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FilmBusiness {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    PersonServiceClient personServiceClient;

    @Autowired
    OmdbServiceClient omdbServiceClient;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    public FilmBusiness() {}

    public ArrayList<Film> getAll() {
        ArrayList<Film> list =(ArrayList<Film>) this.filmRepository.findAll();
        list.forEach(film -> film.setInstanceId(this.instanceId));
        return list;
    }

    public ArrayList<Film> search(String query) {
        ArrayList<Film> list =(ArrayList<Film>) this.filmRepository.findByTitleIgnoreCaseContaining(query);
        list.forEach(film -> film.setInstanceId(this.instanceId));
        return list;
    }

    public List<TakesPart> getAllPeopleForFilm(String filmId) {
        AtomicReference<List<TakesPart>> list = new AtomicReference<List<TakesPart>>();

        // Create SOAP request.
        GetPeopleForFilm getPeopleForFilm = new GetPeopleForFilm();
        getPeopleForFilm.setFilmId(filmId);

        // Make query.
        CompletableFuture<GetPeopleForFilmResponse> c = personServiceClient.getPeopleForFilm(getPeopleForFilm);
        c.thenAccept(getPeopleForFilmResponse -> list.set(getPeopleForFilmResponse.getReturn()));
        c.join();

        return list.get();
    }

    public Film one(String filmId, int deep) {
        // Used to contain all the completable futures and wait to respond until all of them are completed.
        LinkedList<CompletableFuture> linkedList = new LinkedList<>();

        // Get film from db.
        Optional<Film> optional = filmRepository.findByImdbID(filmId);

        // Check if film exists in db.
        if (!optional.isPresent()) {
            throw new FilmNotFoundException();
        } else {
            // Set instanceId.
            optional.get().setInstanceId(this.instanceId);
        }

        // Check if we want score and  people in this film.
        if (deep == 1) {
            // Get score for each film.
            String apiKey = System.getenv("OMDB_API_KEY");
            System.out.println("API_KEY: " + apiKey);

            // Ask imdb for film ratings.
            CompletableFuture c = omdbServiceClient
                    .getFilmById(optional.get().getImdbID(), apiKey)
                    .thenAccept(film -> {
                        if (film == null) {
                            // i.e. fallback factory.
                            optional.get().setRatings(new LinkedList<>());
                        } else {
                            optional.get().setRatings(film.getRatings());
                        }
                    });

            linkedList.add(c);

            // Create SOAP request.
            GetPeopleForFilm getPeopleForFilm = new GetPeopleForFilm();
            getPeopleForFilm.setFilmId(filmId);

            // Make request.
            CompletableFuture<GetPeopleForFilmResponse> c1 = personServiceClient.getPeopleForFilm(getPeopleForFilm);
            linkedList.add(c1);
            c1.thenAccept(getPeopleForFilmResponse -> optional.get().setPeople(getPeopleForFilmResponse.getReturn()));
        }

        // Wait before return.
        linkedList.forEach(CompletableFuture::join);

        return optional.get();
    }
}
