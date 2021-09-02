package com.lomuto.lmbackend.services;

import com.lomuto.lmbackend.repositories.ActorRepository;
import com.lomuto.lmbackend.repositories.GenreRepository;
import com.lomuto.lmbackend.repositories.MovieRepository;
import com.lomuto.lmbackend.exceptions.*;
import com.lomuto.lmbackend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    EntityManager em;

    @Transactional(readOnly = true)
    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Movie> getAll(int pageNumber, int pageSize, String sortBy){
        Pageable paging= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Movie> pagedResult = movieRepository.findAll(paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }
        else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Movie> getByTitleContaining(String title){
        return movieRepository.findByTitleContaining(title);
    }

    @Transactional(readOnly = true)
    public List<Movie> getByTitleContaining(String title, int pageNumber, int pageSize, String sortBy){
        Pageable paging= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Movie> pagedResult = movieRepository.findByTitleContaining(title,paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }
        else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Movie> getByGenre(String name){
        return movieRepository.findByGenre(name);
    }

    @Transactional(readOnly = true)
    public List<Movie> getByGenre(String title, int pageNumber, int pageSize, String sortBy){
        Pageable paging= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Movie> pagedResult = movieRepository.findByGenre(title,paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }
        else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Movie> getByActor(String name){
        return movieRepository.findByActor(name);
    }

    @Transactional(readOnly = true)
    public List<Movie> getByActor(String title, int pageNumber, int pageSize, String sortBy){
        Pageable paging= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Movie> pagedResult = movieRepository.findByActor(title,paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }
        else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Movie> getByDirector(String title){
        return movieRepository.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Movie> getByDirector(String title, int pageNumber, int pageSize, String sortBy){
        Pageable paging= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Movie> pagedResult = movieRepository.findByDirector(title,paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }
        else{
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Movie> getByAdvanced(String title, Float maxPrice, Float minPrice, Integer releaseYear, String director, String genre, String actor){
        return movieRepository.findByAdvanced(title, maxPrice, minPrice,releaseYear, director, genre, actor);
    }

    /*
        I for-each su generi e attori sono per inserirli qualora non presenti nel database.
        Poichè la chiave è un id numerico, dato il titolo è necessario ricercare nuovamente la chiave, la merge non funziona altrimenti.
     */

    @Transactional(readOnly = false)
    public Movie addMovie(Movie movie) {
        if(movie.getTitle() != null && movieRepository.existsByTitle(movie.getTitle()))
            throw new MovieTitleAlreadyExistsException();
        for(Genre g : movie.getGenres()){
            if(!genreRepository.existsByName(g.getName())) em.persist(g);
            else{
                g.setId(genreRepository.findByName(g.getName()).getId());
            };
        }
        for(Actor a : movie.getActors()){
            if(!actorRepository.existsByName(a.getName())) em.persist(a);
            else a.setId(actorRepository.findByName(a.getName()).getId());
        }
        return movieRepository.save(movie);
    }
}
