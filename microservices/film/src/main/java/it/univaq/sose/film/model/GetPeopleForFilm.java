
package it.univaq.sose.film.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for getPeopleForFilm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPeopleForFilm"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="filmId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "getPeopleForFilm", namespace = "http://service.sose.univaq.it/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPeopleForFilm", propOrder = {
    "filmId"
})
public class GetPeopleForFilm {

    @XmlElement(required = true)
    protected String filmId;

    /**
     * Gets the value of the filmId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilmId() {
        return filmId;
    }

    /**
     * Sets the value of the filmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilmId(String value) {
        this.filmId = value;
    }

}
