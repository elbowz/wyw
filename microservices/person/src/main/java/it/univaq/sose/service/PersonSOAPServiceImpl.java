package it.univaq.sose.service;

import it.univaq.sose.business.PersonBusiness;
import it.univaq.sose.model.TakesPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonSOAPServiceImpl implements PersonSOAPService {

    @Autowired
    PersonBusiness personBusiness;

    @Override
    public List<TakesPart> getPeopleForFilm(String filmId) throws Exception {
        return personBusiness.getPeopleForFilm(filmId);
    }

    @Override
    public TakesPart addPersonToFilm(TakesPart takesPart) throws Exception {
        return personBusiness.addPersonToFilm(takesPart);
    }
}
