package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.service.CommentServiceImpl;
import com.edu.lnu.mongoDbPpoject.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostServiceImpl service;
    @Autowired
    private CookieProvider cookie;
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/all")
    public List<Post> getAllPosts(){
       return service.showAll();
    }

    @GetMapping("/{nickname}")
    public List<Post> getAllByUser(@PathVariable  String nickname){
        return service.getAllByUserNickname(nickname);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post){

        return ResponseEntity.status(HttpStatus.OK).body(service.createPost(post));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deletePost(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> editPost(@RequestBody String text, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePost(text, id));
    }
    @PutMapping("/comment")
    public ResponseEntity<Post> createComment( @RequestBody String postId,String text, HttpServletRequest request){
        String username = cookie.readCookie(request, "username");
        return ResponseEntity.status(HttpStatus.OK).body(commentService.addComment(postId, text, username));

    }
}
