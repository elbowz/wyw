package it.univaq.sose.repository;

import it.univaq.sose.model.TakesPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<TakesPart, Long> {
    List<TakesPart> findTakesPartsByIdFilmId(String filmId);
}