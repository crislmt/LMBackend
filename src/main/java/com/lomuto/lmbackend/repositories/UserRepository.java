package com.lomuto.lmbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lomuto.lmbackend.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    //TODO
}
