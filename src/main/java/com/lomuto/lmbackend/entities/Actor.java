package com.lomuto.lmbackend.entities;

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
@Table(name="actor", schema="store")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Basic
    @Column(name="first_name", length = 50, nullable = true)
    private String first_name;

    @Basic
    @Column(name="last_name", length = 50, nullable = true)
    private String last_name;

    @Basic
    @Column(name = "nationality", length = 50, nullable = true)
    private String nationality;
}
