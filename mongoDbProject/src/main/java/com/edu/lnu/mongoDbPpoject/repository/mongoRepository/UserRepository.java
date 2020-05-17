package com.edu.lnu.mongoDbPpoject.repository.mongoRepository;

import com.edu.lnu.mongoDbPpoject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findBy_id(String id);
    User findByNickName(String nickName);
    User findBySurname(String surname);
    User findByName(String name);
    boolean existsByNickName(String nickname);
    List<User> getAllByFollowingIsNotNull();

}
