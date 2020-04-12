package it.univaq.sose.business;

import it.univaq.sose.model.TakesPart;
import it.univaq.sose.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonBusiness {

    @Autowired
    PersonRepository repository;

    public PersonBusiness(){}

    public List<TakesPart> getPeopleForFilm(String filmId) {
        ArrayList<TakesPart> list = (ArrayList<TakesPart>) repository.findTakesPartsByIdFilmId(filmId);
        System.out.println(list);
        return list;
    }
}