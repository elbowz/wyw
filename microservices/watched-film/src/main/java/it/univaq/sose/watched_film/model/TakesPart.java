package it.univaq.sose.watched_film.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TakesPart {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TakesPartKey id;

    private String role;
    private Person person;

    public TakesPart() {
    }

    public TakesPartKey getId() {
        return id;
    }

    public void setId(TakesPartKey id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "TakesPart{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", person=" + person +
                '}';
    }
}
