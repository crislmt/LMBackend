package com.lomuto.lmbackend;

import com.lomuto.lmbackend.entities.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class Tests {

    @Autowired
    EntityManager em;

    @Test
    public void test(){
        Movie m1 = new Movie(); m1.setTitle("Interstellar");
        em.getTransaction();
        em.persist(m1);
        em.getTransaction().commit();
    }
    public static void main(String[] args) {
       new Tests().test();
    }
}
