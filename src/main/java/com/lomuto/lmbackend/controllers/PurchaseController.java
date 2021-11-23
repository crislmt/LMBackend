package com.lomuto.lmbackend.controllers;

import com.lomuto.lmbackend.entities.MoviePurchase;
import com.lomuto.lmbackend.entities.Purchase;
import com.lomuto.lmbackend.entities.User;
import com.lomuto.lmbackend.exceptions.MovieQuantityUnavailableException;
import com.lomuto.lmbackend.exceptions.NoSuchUserException;
import com.lomuto.lmbackend.services.PurchaseService;
import com.lomuto.lmbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import support.Authentication.Utils;
import support.ResponseMessage;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;



    @PostMapping("/addPurchase")
    @PreAuthorize("hasAnyAuthority('user')")
    public ResponseEntity addPurchase(@RequestBody List<MoviePurchase> movie_purchaseList){
        try{
            User u= userService.getByUsername(Utils.getEmail());
            Purchase p=new Purchase();
            p.setUser(u);
            p.setPurchaseMovies(movie_purchaseList);
            purchaseService.addPurchase(p);
            return new ResponseEntity(p, HttpStatus.OK);
        }
        catch (MovieQuantityUnavailableException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity issue");
        }
        catch(NoSuchUserException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authentication issue");
        }
    }
}
