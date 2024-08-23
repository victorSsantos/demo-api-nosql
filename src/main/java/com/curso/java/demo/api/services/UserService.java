package com.curso.java.demo.api.services;


import com.curso.java.demo.api.domains.User;
import com.curso.java.demo.api.repositories.UserRepository;
import com.curso.java.demo.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
