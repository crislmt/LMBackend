package com.lomuto.lmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;


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
    @Column(name="release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;

    @Basic
    @Column(name="director", length = 50)
    private String director;

    @Basic
    @Column(name="price")
    private float price;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
    @JsonIgnore
    List<Movie_Purchase> purchases;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="movie_actor", joinColumns = {@JoinColumn(name="movie")}, inverseJoinColumns = {@JoinColumn(name="actor")})
    List<Actor> actors;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="movie_genre", joinColumns = {@JoinColumn(name="movie")}, inverseJoinColumns = {@JoinColumn(name="genre")})
    List<Genre> genres;

    @Version
    @Column(name="version")
    @JsonIgnore
    private long version;

}
