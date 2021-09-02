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
    @Column(name= "name", length = 100, nullable = false)
    private String name;

    @Basic
    @Column(name = "nationality", length = 50)
    private String nationality;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<Movie> movies;
}
