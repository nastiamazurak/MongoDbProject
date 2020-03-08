package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.Comment;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    CookieProvider cookie;

    /*@PostMapping()
    public void createComment( @RequestBody String postId, String text, HttpServletRequest request){
        String username = cookie.readCookie(request, "username");
       commentService.addComment(postId, text, username);

    }*/
}
