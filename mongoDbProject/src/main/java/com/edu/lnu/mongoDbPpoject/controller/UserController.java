package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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
    public ResponseEntity<User> updateUserInfo(@RequestBody User user)  {
        User userOld = userService.getCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserInfo(user, userOld.getNickName()));
    }
    @GetMapping("/{username}/friends")
    public List<String> showFriends(@PathVariable String username){
        return userService.getFriends(username);
    }

    @PostMapping("/friends")
    public String addFriend(@RequestBody String nickname){
        return userService.addFriend(nickname);
    }
    @DeleteMapping("/friends/{friend}")
    public String deleteFriend(@PathVariable String friend){
        return userService.deleteFriend(friend);
    }

    @GetMapping("/isfriend/{nickName}")
    public boolean isFriend(@PathVariable String nickName){
        return userService.isFriend(nickName);
    }

}
