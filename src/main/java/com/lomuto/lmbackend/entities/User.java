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

//OK
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code", nullable=false)
    private int id;

    @Basic
    @Column(name="first_name", nullable=true, length = 50)
    private String first_name;

    @Basic
    @Column(name="last_name", nullable=true, length = 50)
    private String last_name;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name="birth_date", nullable=true)
    private Date birth_date;

    @Basic
    @Column(name="email", nullable = true, length = 90)
    private String email;

    @Basic
    @Column(name="address", nullable = true, length = 150)
    private String address;

    @OneToMany(mappedBy="user", cascade=CascadeType.MERGE)
    @JsonIgnore
    private List<Purchase> purchases;
}
