package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public List<Post> showAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> getAllByUserNickname(String nickname) {
       return repository.getAllByAuthorNickName(nickname);
    }
}
