package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Role;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.security.AuthBody;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {

    User getUserInfo(String username);

    User getCurrentUser();

    User updateUserInfo(User user, String username);

    User findUser(String feature);

    boolean registerUser(User user);

    boolean comparePasswordLogin(AuthBody body, PasswordEncoder passwordEncoder);

    User findUserByUserName(String username);

    boolean existsUserByUsername(String username);

    List<String> getFriends(String nickname);

     String addFriend(String Friend);

    String deleteFriend(String Friend);

    boolean isFriend(String nickName);

}

