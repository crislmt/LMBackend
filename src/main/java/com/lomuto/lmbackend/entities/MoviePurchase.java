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
public class MoviePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="movie")
    private Movie movie;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name="purchase")
    private Purchase purchase;

    @Basic
    @Column(name="quantity", nullable = true)
    private int quantity;
}
