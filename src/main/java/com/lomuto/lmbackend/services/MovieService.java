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


    public static void main(String[] args){
        Movie m1=new Movie();
        m1.setTitle("Blade Runner");
        MovieService ms=new MovieService();
        ms.addMovie(m1);
    }
}
