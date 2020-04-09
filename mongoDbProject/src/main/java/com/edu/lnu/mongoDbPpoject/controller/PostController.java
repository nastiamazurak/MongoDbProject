package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.service.CommentServiceImpl;
import com.edu.lnu.mongoDbPpoject.service.PostServiceImpl;
import com.edu.lnu.mongoDbPpoject.service.UserServiceImpl;
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
    private PostServiceImpl postService;
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/all")
    public List<Post> getAllPosts(){
       return postService.showAll();
    }

    @GetMapping("/{username}")
    public List<Post> getAllByUser(@PathVariable String username){
        return postService.getAllByUserNickname(username);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return ResponseEntity.status(HttpStatus.OK).body(postService.createPost(post));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> editPost(@RequestBody String postInfo[]){
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(postInfo[0], postInfo[1]));
    }
    @PostMapping("/comment")
    public ResponseEntity<Post> createComment(@RequestBody String comment[]){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.addComment(comment[0],comment[1]));
    }

    /*@GetMapping("/comment/count/{postId}")
    public ResponseEntity<Integer> countComment(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.countPostsByUser(username));
    }*/
}
