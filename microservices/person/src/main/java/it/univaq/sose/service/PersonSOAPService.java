package it.univaq.sose.service;

import it.univaq.sose.model.TakesPart;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;


@WebService
public interface PersonSOAPService {
    @WebMethod
    public List<TakesPart> getPeopleForFilm(
            @XmlElement(required=true)
            @WebParam(name = "filmId") String filmId) throws Exception;
}
