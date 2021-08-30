package com.lomuto.lmbackend.repositories;

import com.lomuto.lmbackend.entities.Movie;
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

    @Query(value="select m from Movie m join m.genres g where ")
    List<Movie> findByGenre(String genre);

    //getByActor
    @Query(value="select m.* from movie_genre as mg, movie as m where mg.movie=m.id", nativeQuery = true)
    List<Movie> findByActor(String actor);

    //NATIVE QUERY, CHANGE IT ASAP TODO
    @Query(value="select * from Movie as m where year(m.release_date)=?0", nativeQuery = true)
    List<Movie> findByReleaseDate(int year);

    //AdvanceResearch TODO


    boolean existsByTitle(String title);
}
