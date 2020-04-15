package it.univaq.sose.watched_film.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

public class Ratings {
    private LinkedList<Rate> ratings;

    public Ratings() {
        this.ratings = new LinkedList<>();
    }

    @JsonProperty("omdbRatings")
    public LinkedList<Rate> getRatings() {
        return ratings;
    }

    @JsonProperty("ratings")
    public void setRatings(LinkedList<Rate> ratings) {
        this.ratings = ratings;
    }

    public void addRatings(Rate rate){
        this.ratings.add(rate);
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "ratings=" + ratings +
                '}';
    }
}