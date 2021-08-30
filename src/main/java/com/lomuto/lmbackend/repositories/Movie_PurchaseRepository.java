package com.lomuto.lmbackend.repositories;

import com.lomuto.lmbackend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Movie_PurchaseRepository extends JpaRepository<Movie_Purchase, Integer> {
}
