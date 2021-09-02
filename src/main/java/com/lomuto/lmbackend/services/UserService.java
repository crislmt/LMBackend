package com.lomuto.lmbackend.services;

import com.lomuto.lmbackend.entities.User;
import com.lomuto.lmbackend.exceptions.UserEmailAlreadyExistsException;
import com.lomuto.lmbackend.exceptions.UserEmailMissingArgumentException;
import com.lomuto.lmbackend.exceptions.UserUsernameAlreadyExistsException;
import com.lomuto.lmbackend.exceptions.UserUsernameMissingArgumentException;
import com.lomuto.lmbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User addUser(User user) throws UserEmailAlreadyExistsException, UserUsernameAlreadyExistsException, UserEmailMissingArgumentException, UserUsernameMissingArgumentException{
        if(user.getUsername()==null) throw new UserEmailMissingArgumentException();
        if(user.getEmail()==null) throw new UserUsernameMissingArgumentException();
        if(userRepository.existsByUsername(user.getUsername())) throw new UserUsernameAlreadyExistsException();
        if(userRepository.existsByEmail(user.getEmail())) throw new UserEmailAlreadyExistsException();
        return userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public List<User> getAll(User user){
        return userRepository.findAll();
    }
}
