package it.univaq.sose.watched_film.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity()
@Table(name = "watched")
@JsonIgnoreProperties(value={"user", "film"}, allowGetters = true, allowSetters = false)
public class Watched {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String filmId;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @Transient
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY) doesn't work idk why.
    private Film film;

    @Transient
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY) doesn't work idk why.
    private User user;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String instanceId;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "Watched{" +
                "id=" + id +
                ", userId=" + userId +
                ", filmId='" + filmId + '\'' +
                ", film=" + film +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", instanceId=" + instanceId +
                '}';
    }
}
