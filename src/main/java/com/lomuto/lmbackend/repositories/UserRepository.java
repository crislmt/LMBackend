package com.lomuto.lmbackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lomuto.lmbackend.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //Username
    List<User> findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);

    //Email
    List<User> findByEmail(String email);
    void deleteByEmail(String username);
    boolean existsByEmail(String email);

}
