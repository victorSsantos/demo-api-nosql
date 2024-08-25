package com.curso.java.demo.api.controllers;

import com.curso.java.demo.api.controllers.util.URL;
import com.curso.java.demo.api.domains.Post;
import com.curso.java.demo.api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostController {

    @Autowired
    private PostService service;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Post>> findAll(){
        List<Post> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value="/title", method= RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text",defaultValue = "") String text){
        List<Post> result = service.findByTitle(URL.decodeParam(text));
        return ResponseEntity.ok(result);
    }
}
