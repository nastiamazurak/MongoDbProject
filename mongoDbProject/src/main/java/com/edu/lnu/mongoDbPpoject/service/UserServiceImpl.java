package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Role;
import com.edu.lnu.mongoDbPpoject.model.SecUserDetails;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.repository.RoleRepository;
import com.edu.lnu.mongoDbPpoject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User getUserInfo(String id) {
        return userRepository.findBy_id(id);
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User findUserByNickName(String nickName) {
        User user = userRepository.findByNickName(nickName);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
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

    @Override
    public User loginUser(User user) {
        if (userRepository.findByNickName(user.getNickName())== null){
            //return exception (write it before)
        }
        return userRepository.findByNickName(user.getNickName());
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}

