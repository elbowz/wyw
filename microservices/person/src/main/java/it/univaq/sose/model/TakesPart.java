package it.univaq.sose.model;

import javax.persistence.*;

@Entity
public class TakesPart {
    @EmbeddedId
    private TakesPartKey id;

    @Column(insertable=false, updatable=false)
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
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
