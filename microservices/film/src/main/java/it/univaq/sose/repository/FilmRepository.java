package it.univaq.sose.repository;

import it.univaq.sose.model.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {
    Film findByTitle(String title);
}
