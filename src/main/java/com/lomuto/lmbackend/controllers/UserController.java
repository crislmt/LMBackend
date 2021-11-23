package com.lomuto.lmbackend.controllers;

import com.lomuto.lmbackend.entities.User;
import com.lomuto.lmbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import support.ResponseMessage;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('user')")
    public ResponseEntity getUserInfo(@RequestParam String username){
        User result=userService.getByUsername(username);
        if(result==null) return new ResponseEntity<>(new ResponseMessage("No such user"), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
