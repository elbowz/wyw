package it.univaq.sose.watched_film.model;

public class TakesPart {
    private String role;
    private Person person;

    public TakesPart() {
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
                ", role='" + role + '\'' +
                ", person=" + person +
                '}';
    }
}
