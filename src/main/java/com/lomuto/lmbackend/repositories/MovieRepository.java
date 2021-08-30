package com.lomuto.lmbackend.repositories;

import com.lomuto.lmbackend.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    //Title
    List<Movie> findByTitle(String title);
    List<Movie> findByTitleLike(String title);
    Page<Movie> findByTitle(String title, Pageable pageable);
    Page<Movie> findByTitleLike(String title, Pageable pageable);
    boolean existsByTitle(String title);


    //Director
    List<Movie> findByDirector(String director);
    List<Movie> findByDirectorLike(String director);
    Page<Movie> findByDirector(String director, Pageable pageable);
    Page<Movie> findByDirectorLike(String director, Pageable pageable);


    //Genre
    @Query(value="select m from Movie m inner join m.genres g where g.name=:genre")
    List<Movie> findByGenre(String genre);
    @Query(value="select m from Movie m inner join m.genres g where g.name=:genre")
    Page<Movie> findByGenre(String genre, Pageable pageable);

    //Actor
    @Query(value="select m from Movie m inner join m.actors a where a.first_name like :name and a.last_name like :surname")
    Page<Movie> findByActorLikePage(String name, String surname);
    @Query(value="select m from Movie m inner join m.actors a where a.first_name like :name and a.last_name like :surname")
    List<Movie> findByActorLike(String name, String surname);

    //NATIVE QUERY, CHANGE IT ASAP. List is for debug TODO
    @Query(value="select * from Movie as m where year(m.release_date)=?0", nativeQuery = true)
    Page<Movie> findByReleaseDatePage(int year, Pageable pageable);
    @Query(value="select * from Movie as m where year(m.release_date)=?0", nativeQuery = true)
    List<Movie> findByReleaseDate(int year);

    //AdvanceResearch TODO
    //Ricerca per: Titolo, Prezzo,
}
