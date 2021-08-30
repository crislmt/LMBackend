package com.lomuto.lmbackend.entities;

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
    @Column(name="name", nullable=true)
    private String name;

    //Fetch type required - TODO
    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;

}
