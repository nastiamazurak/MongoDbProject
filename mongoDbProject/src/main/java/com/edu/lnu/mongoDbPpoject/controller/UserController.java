package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private CookieProvider cookie;

    @GetMapping()
    public User getCurrentUser(){
        return  userService.getCurrentUser();
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfo(username));
    }
    @PutMapping()
    public ResponseEntity<User> updateUserInfo(@RequestBody User user){
        User userOld = userService.getCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserInfo(user, userOld.getNickName()));
    }

}
