package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private CookieProvider cookie;

    @GetMapping()
    public ResponseEntity getUser(){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userService.getCurrentUserName());
        return ResponseEntity.ok(model);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByNickName(username));
    }
    @PutMapping()
    public ResponseEntity<User> updateUserInfo(@RequestBody User user, HttpServletRequest request){
        String username = cookie.readCookie(request, "username");
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserInfo(user, username));
    }

}
