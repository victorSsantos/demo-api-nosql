package com.curso.java.demo.api.repositories;

import com.curso.java.demo.api.domains.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
