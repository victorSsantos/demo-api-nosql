package com.curso.java.demo.api.controllers;

import com.curso.java.demo.api.domains.User;

import com.curso.java.demo.api.dto.UserDTO;
import com.curso.java.demo.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> result = service.findAll().stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User result = service.findById(id);
        return ResponseEntity.ok(new UserDTO(result));
    }
}
