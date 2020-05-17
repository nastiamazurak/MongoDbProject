package com.edu.lnu.mongoDbPpoject.security;

import com.edu.lnu.mongoDbPpoject.exception.constant.ErrorMessage;
import com.edu.lnu.mongoDbPpoject.exception.JwtAuthenticationExeption;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.service.mongoService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(nickName);
        if (user == null)
            throw new JwtAuthenticationExeption(ErrorMessage.DOESNT_AUTHENTICATED + nickName);
        return new UserPrincipal(user);
    }
}
