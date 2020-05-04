package it.univaq.sose.film.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import java.util.LinkedList;
import java.util.List;

@Entity()
@Table(name = "film")
public class Film {
    @Id
    private String imdbID;

    private String title;
    private int year;
    private String plot;
    private String language;
    private String production;
    private String poster;

    @Transient
    private LinkedList<Rate> ratings;

    @Transient
    private List<TakesPart> people;

    public Film() {
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<TakesPart> getPeople() {
        return people;
    }

    public void setPeople(List<TakesPart> people) {
        this.people = people;
    }

    public LinkedList<Rate> getRatings() {
        return ratings;
    }

    public void setRatings(LinkedList<Rate> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Film{" +
                "imdbID='" + imdbID + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", production='" + production + '\'' +
                ", poster='" + poster + '\'' +
                ", people='" + people + '\'' +
                '}';
    }
}

