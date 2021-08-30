package com.lomuto.lmbackend.services;

import com.lomuto.lmbackend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.lomuto.lmbackend.entities.*;
import com.lomuto.lmbackend.exceptions.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public void deleteByTitle(String title) throws MovieDontExistException{
        if(!movieRepository.existsByTitle(title)) throw new MovieDontExistException();
        movieRepository.deleteByTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Movie> findByTitle(String title) throws MovieDontExistException{
        if(!movieRepository.existsByTitle(title)) throw new MovieDontExistException();
        return movieRepository.findByTitle(title);

    }

    public static void main(String[] args){
        Movie m1=new Movie();
        m1.setTitle("Blade Runner");
        MovieService ms=new MovieService();
        ms.addMovie(m1);
    }
}
