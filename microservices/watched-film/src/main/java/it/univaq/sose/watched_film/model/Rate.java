package it.univaq.sose.watched_film.model;

public class Rate {
    private String source;
    private String value;

    public Rate() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "source='" + source + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
