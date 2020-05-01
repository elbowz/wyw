package it.univaq.sose.watched_film.repository;

import it.univaq.sose.watched_film.model.Watched;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchedRepository extends JpaRepository<Watched, Long> {
    List<Watched> findWatchedByUserId(long userId);

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END " +
            "FROM Watched w WHERE w.filmId = :filmId AND w.userId = :userId")
    Boolean hasUserWatchedFilm(@Param("userId") long userId, @Param("filmId") String filmId);
}