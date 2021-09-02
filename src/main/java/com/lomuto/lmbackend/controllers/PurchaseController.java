package com.lomuto.lmbackend.controllers;

import com.lomuto.lmbackend.entities.MoviePurchase;
import com.lomuto.lmbackend.entities.Purchase;
import com.lomuto.lmbackend.exceptions.MovieQuantityUnavailableException;
import com.lomuto.lmbackend.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import support.ResponseMessage;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Purchase purchase){
        try{
            return new ResponseEntity<>(purchaseService.addPurchase(purchase), HttpStatus.OK);
        }
        catch(MovieQuantityUnavailableException e){
            return new ResponseEntity(new ResponseMessage("Quantity unavailable"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addPurchase")
    public ResponseEntity addPurchase(@RequestBody List<MoviePurchase> movie_purchaseList){
        try{
            Purchase p=new Purchase();
            p.setPurchaseMovies(movie_purchaseList);
            purchaseService.addPurchase(p);
            return new ResponseEntity(p, HttpStatus.OK);
        }
        catch (MovieQuantityUnavailableException e){
            return new ResponseEntity("Quantity unavailable", HttpStatus.BAD_REQUEST);
        }
    }
}
