package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Override
    public Post createPost(Post post, String postId) {
        post.setId(postId);
        post.setDate(new Timestamp(new Date().getTime()));
        return repository.save(post);
    }

    @Override
    public List<Post> showAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> getAllByUserNickname(String nickname) {
       return repository.getAllByAuthorNickName(nickname);
    }

    @Override
    public Post updatePost(String text, String postId) {
       // CommentDto oldCommentDto = getCommentIfUserHasAccessToThisComment(commentId);
        Post oldPost = repository.getById(postId);
        oldPost.setText(text);
        return repository.save(oldPost);
    }

    @Override
    public Post deletePost(String postId) {
        //getCommentIfUserHasAccessToThisComment(commentId);
        Post oldPost = repository.getById(postId);
        repository.deleteById(postId);
        return oldPost;
    }
}
