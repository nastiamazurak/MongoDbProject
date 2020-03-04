package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.repository.RoleRepository;
import com.edu.lnu.mongoDbPpoject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User getUserInfo(String id) {
        return userRepository.findBy_id(id);
    }

    @Override
    public String getCurrentUserName() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
           username = principal.toString();
        }
        return username;
    }

    @Override
    public User updateUserInfo(User user) {
        return null;
    }

    @Override
    public User findUser(String feature) {
        if (userRepository.findByName(feature) != null){
            return userRepository.findByName(feature);
        }
        else if (userRepository.findBySurname(feature)!= null){
            return userRepository.findBySurname(feature);
        }
        else if (userRepository.findByNickName(feature)!= null){
            return userRepository.findByNickName(feature);
        }
        else {
            return null;
        }
    }
}

