package com.lomuto.lmbackend.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="purchase", schema="store")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseTime;


    @OneToMany
    private List<Movie_Purchase> movies;
}
