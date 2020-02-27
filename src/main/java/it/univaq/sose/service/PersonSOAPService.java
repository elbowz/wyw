package it.univaq.sose.service;

import it.univaq.sose.model.Person;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PersonSOAPService {
    @WebMethod
    public Person getPerson(long l) throws Exception;
}
