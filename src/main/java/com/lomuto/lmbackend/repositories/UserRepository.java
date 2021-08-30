package com.lomuto.lmbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lomuto.lmbackend.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //List<User> findByfirst_name(String first_name);
    //List<User> findBylast_name(String last_name);
}
