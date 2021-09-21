package com.lomuto.lmbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="user", schema="store")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private int id;

    @Basic
    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Basic
    @Column(name="email", nullable = false, length = 90)
    private String email;

    @Basic
    @Column(name="first_name", nullable=true, length = 50)
    private String firstName;

    @Basic
    @Column(name="last_name", nullable=true, length = 50)
    private String lastName;

    @Basic
    @Column(name="address", nullable = true, length = 150)
    private String address;

    @OneToMany(mappedBy="user", cascade=CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<Purchase> purchases;
}
