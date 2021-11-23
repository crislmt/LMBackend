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
    Page<Movie> findByTitle(String title, Pageable pageable);
    Page<Movie> findByTitleContaining(String title, Pageable pageable);
    boolean existsByTitle(String title);
    void deleteByTitle(String title);


    //Director
    Page<Movie> findByDirector(String director, Pageable pageable);

    //Genre
    @Query(value="select m from Movie m inner join m.genres g where g.name=:genre")
    List<Movie> findByGenre(String genre);
    @Query(value="select m from Movie m inner join m.genres g where g.name=:genre")
    Page<Movie> findByGenre(String genre, Pageable pageable);

    //Actor
    @Query(value="select m from Movie m inner join m.actors a where a.name like :name")
    Page<Movie> findByActor(String name, Pageable pageable);
    @Query(value="select m from Movie m inner join m.actors a where a.name like :name")
    List<Movie> findByActor(String name);



    //Year
    @Query(value="select m from Movie m where m.releaseYear=:year")
    Page<Movie> findByReleaseDate(int year, Pageable pageable);
    @Query(value="select m from Movie m where m.releaseYear=:year")
    List<Movie> findByReleaseDate(int year);

    //AdvancedSearch
    @Query( value= "select distinct m from Movie m inner join m.actors a inner join m.genres g " +
            "where (:title is null or m.title like %:title%) and" +
            "(:maxPrice is null or m.price<=:maxPrice) and " +
            "(:minPrice is null or m.price>=:minPrice) and" +
            "(:releaseYear is null or m.releaseYear=:releaseYear) and" +
            "(:director is null or m.director like %:director%) and" +
            "(:actor is null or a.name like %:actor%) and"+
            "(:genre is null or g.name like :genre)")
    List<Movie> findByAdvanced(String title, Float maxPrice, Float minPrice, Integer releaseYear, String director, String genre, String actor);
}
