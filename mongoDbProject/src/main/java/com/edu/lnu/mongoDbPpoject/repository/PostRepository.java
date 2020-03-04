package com.edu.lnu.mongoDbPpoject.repository;

import com.edu.lnu.mongoDbPpoject.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> getAllByAuthorNickName(String authorNickName);
    List<Post> findByTextContains(String text);
    List<Post> findAll();
    void deleteById(String id);
    Post getById(String id);
    Post findPostByIdAndAuthorNickName(String id, String username);


}
