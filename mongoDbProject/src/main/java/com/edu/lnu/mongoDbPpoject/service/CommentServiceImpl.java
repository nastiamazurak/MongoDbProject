package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Comment;
import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl  implements CommentService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public Post addComment(String postId, String text, String username) {
            Post post = postRepository.getById(postId);

            if (post.getComments() == null) {
                List<Comment> comments = new ArrayList<>();
                post.setComments(comments);
            }

            List<Comment> comments = post.getComments();
            Comment comment = new Comment();
            comment.setAuthor_nickname(username);
            comment.setDate(new Timestamp(new Date().getTime()));
            comment.setText(text);
            comments.add(comment);
            post.setComments(comments);
            postRepository.save(post);
            return post;
    }

    @Override
    public Comment deleteComment(String postId, String commentId) {
        return null;
    }

    @Override
    public Comment editComment(String commentId, String postId, String text) {
        return null;
    }
}
