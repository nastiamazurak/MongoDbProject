package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping()
    public User findUsersByNick(@PathVariable ("nickName") String nickName){
       //return  userRepository.findByNickName(nickName);
       return service.findUserByNickName(nickName);
    }

    @GetMapping("/{feature}")
    public User getByName(@PathVariable String feature) {
        return service.findUser(feature);
    }

}
