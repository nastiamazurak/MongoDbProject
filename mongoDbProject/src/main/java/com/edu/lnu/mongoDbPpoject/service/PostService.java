package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    List<Post> showAll();
    List<Post> getAllByUserNickname(String nickname);

}
