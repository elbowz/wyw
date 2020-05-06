package it.univaq.sose.watched_film.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class Film {
    private String imdbID;
    private String title;
    private int year;
    private String plot;
    private String language;
    private String production;
    private String poster;

    private LinkedList<Rate> ratings;

    private List<TakesPart> people;

    private String instanceId;

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

    public LinkedList<Rate> getRatings() {
        return ratings;
    }

    public void setRatings(LinkedList<Rate> ratings) {
        this.ratings = ratings;
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

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
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
                ", ratings=" + ratings +
                ", people=" + people +
                '}';
    }
}
