package com.lomuto.lmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="movie_purchase", schema="store")
public class Movie_Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name="movie")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name="purchase")
    private Purchase purchase;

    @Basic
    @Column(name="quantity", nullable = true)
    private int quantity;
}
