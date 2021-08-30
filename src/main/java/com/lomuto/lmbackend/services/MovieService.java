package com.lomuto.lmbackend.services;

import com.lomuto.lmbackend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.lomuto.lmbackend.entities.*;
import com.lomuto.lmbackend.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = false)
    public void addMovie(Movie movie) throws MovieAlreadyExistsException{
        if(movie.getTitle() != null && movieRepository.existsByTitle(movie.getTitle())) throw new MovieAlreadyExistsException();
        movieRepository.save(movie);
    }

    @Transactional(readOnly=true)
    public List<Movie> showAllMovies(){return movieRepository.findAll();}

    @Transactional(readOnly = false) //JUST FOR DEBUGGING TODO
    public void deleteAllMovie(){
         movieRepository.deleteAll();
    }

    @Transactional(readOnly = false)
    public void deleteByTitle(String title) throws NoSuchMovieException {
        if(!movieRepository.existsByTitle(title)) throw new NoSuchMovieException();
        movieRepository.deleteByTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Movie> findByTitle(String title) throws NoSuchMovieException {
        if(!movieRepository.existsByTitle(title)) throw new NoSuchMovieException();
        return movieRepository.findByTitle(title);

    }
}
