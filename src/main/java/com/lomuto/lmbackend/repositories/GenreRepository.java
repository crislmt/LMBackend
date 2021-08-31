package com.lomuto.lmbackend.repositories;
import com.lomuto.lmbackend.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    //Name
    Genre findByName(String name);
    boolean existsByName(String name);

}
