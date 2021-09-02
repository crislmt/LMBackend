package com.lomuto.lmbackend.repositories;

import com.lomuto.lmbackend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePurchaseRepository extends JpaRepository<com.lomuto.lmbackend.entities.MoviePurchase, Integer> {
}
