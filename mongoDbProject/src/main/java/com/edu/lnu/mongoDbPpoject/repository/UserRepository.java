package com.edu.lnu.mongoDbPpoject.repository;

import com.edu.lnu.mongoDbPpoject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findBy_id(String id);
    User findByNickName(String nickName);
    User findBySurname(String surname);
    User findByName(String name);

}
