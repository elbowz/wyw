package it.univaq.sose.service;

import it.univaq.sose.business.PersonBusiness;
import it.univaq.sose.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class PersonSOAPServiceImpl implements PersonSOAPService {
    @Autowired
    PersonBusiness personBusiness;

    @Override
    public Person getPerson(long l) throws Exception {
        Person p = personBusiness.getPersonById(l);
        if (p == null){
            throw new Exception("User not found");
        }
        return p;
    }
}
