package com.lomuto.lmbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePurchaseRepository extends JpaRepository<com.lomuto.lmbackend.entities.MoviePurchase, Integer> {
}
