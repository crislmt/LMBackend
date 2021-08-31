package com.lomuto.lmbackend.repositories;
import com.lomuto.lmbackend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    //Name
    Actor findByName(String name);
    boolean existsByName(String name);

}
