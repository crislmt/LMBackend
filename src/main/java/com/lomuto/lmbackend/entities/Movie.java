package com.lomuto.lmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name="movie", schema="store")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name="title", length = 50)
    private String title;

    @Basic
    @Column(name="quantity")
    private int quantity;

    @Basic
    @Column(name="release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;

    @Basic
    @Column(name="director", length = 50)
    private String director;

    @OneToMany(targetEntity = MovieInPurchase.class, mappedBy = "product", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<MovieInPurchase> moviesInPurchase;

    //TODO
    @OneToMany
    private List<Movie> genres;
    @OneToMany
    private List<Actor> actors;
}
