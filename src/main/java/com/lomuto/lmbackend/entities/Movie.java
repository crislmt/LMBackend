package com.lomuto.lmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @Column(name="title", nullable = false, length = 50)
    private String title;

    @Basic
    @Column(name="quantity")
    private int quantity;

    @Basic
    @Column(name= "release_year", nullable = true)
    private int releaseYear;

    @Basic
    @Column(name="director", length = 50)
    private String director;

    @Basic
    @Column(name="price", nullable = true)
    private float price;

    @OneToMany(targetEntity = MoviePurchase.class, mappedBy = "movie", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<MoviePurchase> moviePurchases;


    @ManyToMany(cascade={CascadeType.MERGE})
    @JoinTable(name="movie_genre", joinColumns = {@JoinColumn(name="movie")}, inverseJoinColumns = {@JoinColumn(name="genre")})
    List<Genre> genres;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="movie_actor", joinColumns = {@JoinColumn(name="movie")}, inverseJoinColumns = {@JoinColumn(name="actor")})
    List<Actor> actors;

    @Basic
    @Column(name="image_url", length = 2083, nullable = true)
    private String imageUrl;

    @Version
    @Column(name="version", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private long version;

}
