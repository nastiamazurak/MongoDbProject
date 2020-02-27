package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.User;

public interface UserService {

    User getUserInfo(String id);
    User getCurrentUser();
    User findUserByNickName(String nickName);
    User updateUserInfo(User user);
    User findUser(String feature);



}
