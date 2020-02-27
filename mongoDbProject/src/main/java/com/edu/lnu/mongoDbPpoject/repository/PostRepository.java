package com.edu.lnu.mongoDbPpoject.repository;

import com.edu.lnu.mongoDbPpoject.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> getAllByAuthorNickName(String authorNickName);
    List<Post> findByTextContains(String text);
    List<Post> findAll();

}
