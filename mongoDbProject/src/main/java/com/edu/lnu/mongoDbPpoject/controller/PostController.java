package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostServiceImpl service;

    @GetMapping("/all")
    public List<Post> getAllPosts(){
       return service.showAll();
    }
    @GetMapping("/{nickname}")
    public List<Post> getAllByUser(@PathVariable  String nickname){
        return service.getAllByUserNickname(nickname);
    }
}
