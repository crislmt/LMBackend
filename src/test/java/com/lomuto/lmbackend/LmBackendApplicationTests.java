package com.lomuto.lmbackend;

import com.lomuto.lmbackend.entities.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class LmBackendApplicationTests {
    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
    }

}
