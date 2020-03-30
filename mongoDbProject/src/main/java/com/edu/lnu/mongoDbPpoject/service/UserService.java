package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Role;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.security.AuthBody;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    User getUserInfo(String username);

    User getCurrentUser();

    User updateUserInfo(User user, String username);

    User findUser(String feature);

    boolean registerUser(User user);

    boolean comparePasswordLogin(AuthBody body, PasswordEncoder passwordEncoder);

    User findUserByUserName(String username);

    boolean existsUserByUsername(String username);
}
    //boolean comparePasswordLogin(AuthenticationRequestDto requestDto, PasswordEncoder passwordEncoder);
