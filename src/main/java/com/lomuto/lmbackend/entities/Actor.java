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
@Table(name="actor", schema="store")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Basic
    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Basic
    @Column(name = "nationality", length = 50, nullable = true)
    private String nationality;

    //Fetch type required - TODO
    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    @ToString.Exclude
    private List<Movie> movies;
}
