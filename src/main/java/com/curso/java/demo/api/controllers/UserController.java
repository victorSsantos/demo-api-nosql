package com.curso.java.demo.api.controllers;

import com.curso.java.demo.api.domains.Post;
import com.curso.java.demo.api.domains.User;

import com.curso.java.demo.api.dto.UserDTO;
import com.curso.java.demo.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @RequestMapping(value="/{id}/posts", method= RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User result = service.findById(id);
        return ResponseEntity.ok(result.getPosts());
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO){
        User result = service.insert(userDTO.fromDTO());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO obj){
        obj.setId(id);
        service.update(obj.fromDTO());
        return ResponseEntity.noContent().build();
    }
}
