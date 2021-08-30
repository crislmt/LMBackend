package com.lomuto.lmbackend.controllers;

import com.lomuto.lmbackend.entities.Movie;
import com.lomuto.lmbackend.exceptions.MovieAlreadyExistsException;
import com.lomuto.lmbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import support.ResponseMessage;

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
}
