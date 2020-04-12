package it.univaq.sose.watched_film.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

public class Ratings {
    private LinkedList<Rate> omdbRatings;

    public Ratings() {
    }

    @JsonProperty("omdbRatings")
    public LinkedList<Rate> getRatings() {
        return omdbRatings;
    }

    public void setRatings(LinkedList<Rate> ratings) {
        this.omdbRatings = ratings;
    }

    public void addRatings(Rate rate){
        this.omdbRatings.add(rate);
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "ratings=" + omdbRatings +
                '}';
    }
}