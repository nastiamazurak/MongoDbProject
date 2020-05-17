package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.Person;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.repository.mongoRepository.UserRepository;
import com.edu.lnu.mongoDbPpoject.repository.neo4JRepository.PersonRepository;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.service.mongoService.UserServiceImpl;
import com.edu.lnu.mongoDbPpoject.service.neo4JService.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    private CookieProvider cookie;

    @Autowired
    private UserRepository userRepository;

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
    @GetMapping("/{username}/following")
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

    @GetMapping("/{nickName}/followers")
    public List<String> getFollowers(@PathVariable String nickName){
        return userService.getFollowers(nickName);
    }

    @GetMapping("/connections/{nickName}")
    public String getConnections(@PathVariable String nickName){
        return personService.getConnectionsPath(nickName);
    }
    @GetMapping("/commonFriends/{nickName}")
    public List<String> getCommonFriends(@PathVariable String nickName){
        return personService.getCommonFriends(nickName);
    }
}
