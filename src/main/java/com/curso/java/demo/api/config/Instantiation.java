package com.curso.java.demo.api.config;

import com.curso.java.demo.api.domains.Post;
import com.curso.java.demo.api.domains.User;
import com.curso.java.demo.api.dto.AuthorDTO;
import com.curso.java.demo.api.dto.CommentDTO;
import com.curso.java.demo.api.repositories.PostRepository;
import com.curso.java.demo.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter fmt = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        userRepo.deleteAll();
        postRepo.deleteAll();

        User u1 = new User(null, "Maria Brown", "maria@gmail.com");
        User u2 = new User(null, "Alex Green", "alex@gmail.com");
        User u3 = new User(null, "Bob Grey", "bob@gmail.com");
        userRepo.saveAll(Arrays.asList(u1,u2,u3));

        Post p1 = new Post(null, LocalDateTime.parse("2018-03-21T15:30:00Z",fmt),"Lets go trip", "Im gonna trip to Sao Paulo, see you later guys ;)", new AuthorDTO(u3));
        Post p2 = new Post(null, LocalDateTime.parse("2020-05-23T12:05:00Z",fmt),"Good Morning", "I wake up Happy today =)", new AuthorDTO(u1));
        CommentDTO c1 = new CommentDTO("Good trip Bro!",LocalDateTime.parse("2018-03-21T15:45:00Z",fmt),new AuthorDTO(u2));
        CommentDTO c2 = new CommentDTO("Enjoy it =)",LocalDateTime.parse("2018-03-22T10:20:00Z",fmt),new AuthorDTO(u1));
        CommentDTO c3 = new CommentDTO("Have a nice day!!",LocalDateTime.parse("2020-03-23T12:30:00Z",fmt),new AuthorDTO(u3));
        p1.getComments().addAll(Arrays.asList(c1,c2));
        p2.getComments().add(c3);
        postRepo.saveAll(Arrays.asList(p1,p2));

        u1.getPosts().addAll(Arrays.asList(p1,p2));
        userRepo.save(u1);

    }
}
