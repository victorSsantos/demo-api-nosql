package com.curso.java.demo.api.services;

import com.curso.java.demo.api.domains.Post;
import com.curso.java.demo.api.repositories.PostRepository;
import com.curso.java.demo.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> findAll(){
        return repo.findAll();
    }

    public Post findById(String id){
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle(String text){
        return repo.findByTitle(text);
    }
}
