package it.univaq.sose.watched_film.repository;

import it.univaq.sose.watched_film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {}