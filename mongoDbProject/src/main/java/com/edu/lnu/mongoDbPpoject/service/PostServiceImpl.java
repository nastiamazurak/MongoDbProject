package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Comment;
import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Post createPost(Post post) {
        User user = userService.getCurrentUser();
        if (!post.getText().equals("") && user != null) {
            post.setDate(new Timestamp(new Date().getTime()));
            post.setAuthorNickName(user.getNickName());
            return repository.save(post);
        }
        else{
            return null;
        }
    }

    @Override
    public Post getPostIfUserHasAccess(String postId) {
        String userName= userService.getCurrentUser().getNickName();
        Post post = repository.findPostByIdAndAuthorNickName(postId, userName);
        return post;
    }

    @Override
    public int countPostsByUser(String username) {
      List<Post> posts =repository.getAllByAuthorNickName(username);
      return posts.size();
    }


    public int countCommentTest(String postID){
        Post post = repository.getById(postID);
        return post.getComments().size();
    }

    @Override
    public List<Post> getPostsByText(String text) {
        return repository.findByTextContains(text);
    }

    @Override
    public List<Post> showAll() {
        return repository.findAllByOrderByDateDesc();
    }

    @Override
    public List<Post> getAllByUserNickname(String nickname) {
       return repository.getAllByAuthorNickNameOrderByDateDesc(nickname);
    }

    @Override
    public Post updatePost(String postId, String text) {
        Post oldPost = getPostIfUserHasAccess(postId);
        oldPost.setText(text);
        return repository.save(oldPost);
    }

    @Override
    public Post deletePost(String postId) {
        Post oldPost = getPostIfUserHasAccess(postId);
        repository.deleteById(postId);
        return oldPost;
    }


}
