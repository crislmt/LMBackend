package com.lomuto.lmbackend.repositories;

import com.lomuto.lmbackend.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByTitle(String title);
    List<Movie> findByTitleLike(String title);

    List<Movie> findByDirector(String director);
    List<Movie> findByDirectorLike(String director);

    //Needs to be checked TODO
    //@Query(value="select m from Movie m inner join m.genres g where g.name=:genre")
    //Page<Movie> findByGenre(String genre);

    //Needs to be checked TODO
    //@Query(value="select m from Movie m inner join m.actors a where a.first_name like :name and a.last_name like :surname")
    //Page<Movie> findByActorLikePage(String name, String surname);
    @Query(value="select m from Movie m inner join m.actors a where a.first_name like :name and a.last_name like :surname")
    List<Movie> findByActorLike(String name, String surname);

    //NATIVE QUERY, CHANGE IT ASAP. List is for debug TODO
    //@Query(value="select * from Movie as m where year(m.release_date)=?0", nativeQuery = true)
    //Page<Movie> findByReleaseDatePage(int year);
    @Query(value="select * from Movie as m where year(m.release_date)=?0", nativeQuery = true)
    List<Movie> findByReleaseDate(int year);

    void deleteByTitle(String title);

    //AdvanceResearch TODO
    boolean existsByTitle(String title);
}
