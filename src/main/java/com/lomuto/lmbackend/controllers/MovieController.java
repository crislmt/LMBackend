package com.lomuto.lmbackend.controllers;

import com.lomuto.lmbackend.entities.Movie;
import com.lomuto.lmbackend.exceptions.MovieTitleAlreadyExistsException;
import com.lomuto.lmbackend.exceptions.NoSuchMovieException;
import com.lomuto.lmbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import support.ResponseMessage;
import support.AdvancedRequest;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity addMovie(@RequestBody Movie movie ){
        try{
            movieService.addMovie(movie);
        }
        catch(MovieTitleAlreadyExistsException e){
            return new ResponseEntity<>(new ResponseMessage("Title already exists"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Added succesfully!"), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity getAll(@RequestParam int pageNumber,@RequestParam int pageSize, @RequestParam String sortBy){
        List<Movie> result=movieService.getAll(pageNumber, pageSize, sortBy);
        if(result.size()==0) return new ResponseEntity<>(new ResponseMessage("No movie found"), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("search/byTitle")
    public ResponseEntity getByTitle(@RequestParam String title, @RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String sortBy){
        List<Movie> result=movieService.getByTitleContaining(title, pageNumber, pageSize, sortBy);
        if(result.size()==0) return new ResponseEntity<>(new ResponseMessage("No movie found"), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("search/byActor")
    public ResponseEntity getByActor(@RequestParam String name,@RequestParam int pageNumber,@RequestParam int pageSize, @RequestParam String sortBy){
        List<Movie> result=movieService.getByActor(name, pageNumber, pageSize, sortBy);
        if(result.size()==0) return new ResponseEntity<>(new ResponseMessage("No movie found"), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("search/byAdvanced")
    public ResponseEntity getByAdvanced(@RequestBody AdvancedRequest ar){
        List<Movie> result=movieService.getByAdvanced(ar.getTitle(),ar.getMaxPrice(), ar.getMinPrice(), ar.getReleaseYear(), ar.getDirector(),ar.getGenre(), ar.getActor());
        if(result.size()==0) return new ResponseEntity<>(new ResponseMessage("No such movie"), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
