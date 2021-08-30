package com.lomuto.lmbackend.controllers;

import com.lomuto.lmbackend.entities.Movie;
import com.lomuto.lmbackend.exceptions.MovieAlreadyExistsException;
import com.lomuto.lmbackend.exceptions.MovieDontExistException;
import com.lomuto.lmbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import support.ResponseMessage;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity create(@RequestBody Movie movie ){
        try{
            movieService.addMovie(movie);
        }
        catch(MovieAlreadyExistsException e){
            return new ResponseEntity<>(new ResponseMessage("Title already exists"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Added succesfully!"), HttpStatus.OK);
    }

    @GetMapping("/getByTitle")
    public ResponseEntity getByTitle(@RequestBody String title){
        try{
            List<Movie> result=movieService.findByTitle(title);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (MovieDontExistException m){
            return new ResponseEntity<>(new ResponseMessage("No such movie"), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("/deleteAll")
    public ResponseEntity deleteAll(){
        movieService.deleteAllMovie();
        return new ResponseEntity<>(new ResponseMessage("All movies cancelled succesfully"), HttpStatus.OK);
    }

    @PostMapping("/deleteByTitle")
    public ResponseEntity deleteByTitle(@RequestBody String title){
        try{
            movieService.deleteByTitle(title);
        }
        catch(MovieDontExistException e){
            return new ResponseEntity<>(new ResponseMessage("No movie with such title"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage(title+"has been succesfully cancelled"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Movie> result=movieService.showAllMovies();
        if(result.size()<=0) return new ResponseEntity<>(new ResponseMessage("No movie available"), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
