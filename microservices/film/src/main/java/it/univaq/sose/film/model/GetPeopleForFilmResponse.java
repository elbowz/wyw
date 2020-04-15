
package it.univaq.sose.film.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for getPeopleForFilmResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPeopleForFilmResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://service.sose.univaq.it/}takesPart" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "getPeopleForFilmResponse", namespace = "http://service.sose.univaq.it/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPeopleForFilmResponse", propOrder = {
    "_return"
})
public class GetPeopleForFilmResponse {

    @XmlElement(name = "return")
    protected List<TakesPart> _return;

    /**
     * Gets the value of the return property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the return property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TakesPart }
     * 
     * 
     */
    public List<TakesPart> getReturn() {
        if (_return == null) {
            _return = new ArrayList<TakesPart>();
        }
        return this._return;
    }

}
