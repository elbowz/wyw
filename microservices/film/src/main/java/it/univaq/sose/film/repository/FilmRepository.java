package it.univaq.sose.film.repository;

import it.univaq.sose.film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByImdbID(String id);
    List<Film> findByTitleIgnoreCaseContaining(String title);
}