package it.univaq.sose.watched_film.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity()
@Table(name = "watched")
public class Watched {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String filmId;

    @Transient
    private Film film;

    @Transient
    private User user;

    public Watched() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Watched{" +
                "id=" + id +
                ", userId=" + userId +
                ", filmId='" + filmId + '\'' +
                ", film=" + film +
                ", user=" + user +
                '}';
    }
}
