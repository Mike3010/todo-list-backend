package com.mikelogan.todolistbackend.repository;

import java.util.UUID;

import com.mikelogan.todolistbackend.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {
    
    public User findByEmail(String email);
}
