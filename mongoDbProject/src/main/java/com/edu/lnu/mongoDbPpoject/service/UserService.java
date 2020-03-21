package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.User;

public interface UserService {

    User getUserInfo(String id);
    String getCurrentUserName();
    User updateUserInfo(User user, String username);
    User findUser(String feature);
    User getUserByNickName(String nickname);

}
