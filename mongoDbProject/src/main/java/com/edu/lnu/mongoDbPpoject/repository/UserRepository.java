package com.edu.lnu.mongoDbPpoject.repository;

import com.edu.lnu.mongoDbPpoject.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findBy_id(String id);
    User findByNickName(String nickName);
    User findBySurname(String surname);
    User findByName(String name);

}
