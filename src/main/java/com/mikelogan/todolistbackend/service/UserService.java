package com.mikelogan.todolistbackend.service;

import java.util.UUID;

import com.mikelogan.todolistbackend.model.User;
import com.mikelogan.todolistbackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User getLoggedInUser() {

        String mailAddress = this.getLoggedInUserUserEmailAdress();
        User user = this.userRepository.findByEmail(mailAddress);
        if(user != null) {
            return user;
        }
        return this.createUser();
    }

    public UUID getLoggedInUserId() {
        User user = this.getLoggedInUser();
        return user.getId();
    }

    private Jwt getLoggedInUserJwt() {

        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt;
    }

    private String getLoggedInUserUserEmailAdress() {
        
        Jwt jwt = this.getLoggedInUserJwt();
        return jwt.getClaim("email");
    }

    private User createUser() {

        Jwt jwt = this.getLoggedInUserJwt();
        String email = this.getLoggedInUserUserEmailAdress();
        String name = jwt.getClaim("name");

        User user = new User(email, name);
        this.userRepository.save(user);
        return user;
    }
}
