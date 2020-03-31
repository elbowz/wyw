package it.univaq.sose.watched_film.repository;

import it.univaq.sose.watched_film.model.Watched;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchedRepository extends JpaRepository<Watched, Long> {
    List<Watched> findWatchedByUserId(long userId);
}