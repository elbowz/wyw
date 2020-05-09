package it.univaq.sose.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TakesPartKey implements Serializable {
    @Column(name = "film_id")
    private String filmId;

    @Column(name = "person_id")
    private int personId;

    @Column(name = "role")
    private String role;

    public TakesPartKey() {
    }

    public TakesPartKey(String filmId, int personId, String role) {
        this.filmId = filmId;
        this.personId = personId;
        this.role = role;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakesPartKey that = (TakesPartKey) o;

        return personId == that.personId &&
                filmId.equals(that.filmId) &&
                role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, personId, role);
    }
}
