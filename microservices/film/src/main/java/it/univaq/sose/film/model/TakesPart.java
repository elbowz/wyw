package it.univaq.sose.film.model;

public class TakesPart {
    private String role;
    private Person person;
    private String instanceId;

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

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "TakesPart{" +
                ", role='" + role + '\'' +
                ", person=" + person +
                '}';
    }
}
