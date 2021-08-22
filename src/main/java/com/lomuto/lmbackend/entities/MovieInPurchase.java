package com.lomuto.lmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class MovieInPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="purchase")
    @JsonIgnore
    @ToString.Exclude
    private Purchase purchase;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="movie")
    private Movie movie;

    @Basic
    @Column(name="quantity", nullable=true)
    private int quantity;
}
