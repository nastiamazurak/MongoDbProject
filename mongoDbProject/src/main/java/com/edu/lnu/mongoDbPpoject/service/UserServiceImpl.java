package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getUserInfo(String id) {
        return repository.findBy_id(id);
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User findUserByNickName(String nickName) {
        return repository.findByNickName(nickName);
    }

    @Override
    public User updateUserInfo(User user) {
        return null;
    }

    @Override
    public User findUser(String feature) {
        if (repository.findByName(feature) != null){
            return repository.findByName(feature);
        }
        else if (repository.findBySurname(feature)!= null){
            return repository.findBySurname(feature);
        }
        else if (repository.findByNickName(feature)!= null){
            return repository.findByNickName(feature);
        }
        else {
            return null;
        }
    }
}
