package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    User getUserInfo(String id);
    User getCurrentUser();
    User findUserByNickName(String nickName);
    User updateUserInfo(User user);
    User findUser(String feature);
    void saveUser(User user);
    User loginUser(User user);
    //List<GrantedAuthority> getUserAuthority(Set<Role> userRoles)



}
