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
    @Query(value="select m from Movie m inner join m.actors a where a.name like :name")
    Page<Movie> findByActorLikePage(String name, Pageable pageable);
    @Query(value="select m from Movie m inner join m.actors a where a.name like :name")
    List<Movie> findByActorLike(String name);

    //Year
    @Query(value="select m from Movie m where m.release_year=:year")
    Page<Movie> findByReleaseDatePage(int year, Pageable pageable);
    @Query(value="select m from Movie m where m.release_year=:year")
    List<Movie> findByReleaseDate(int year);

    //AdvancedSearch
    @Query( value= "select m from Movie m inner join m.actors a inner join m.genres g " +
            "where (:title is null or m.title like :title) and" +
            "(:maxPrice is null or m.price<=:maxPrice) and " +
            "(:minPrice is null or m.price>=:minPrice) and" +
            "(:releaseYear is null or m.release_year=:releaseYear) and" +
            "(:director is null or m.director=:director) and" +
            "(:actor is null or a.name like :actor) and"+
            "(:genre is null or g.name like :genre)")
    List<Movie> advancedSearch(String title, int maxPrice, int minPrice, int releaseYear, String director, String genre, String actor);
}
