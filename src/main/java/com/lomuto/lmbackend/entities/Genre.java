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
@Table(name="genre", schema="store")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private int id;

    @Basic
    @Column(name="name", nullable=false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "genres", cascade = {CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    private List<Movie> movies;

}
