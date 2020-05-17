package com.edu.lnu.mongoDbPpoject.service.mongoService;

import com.edu.lnu.mongoDbPpoject.model.Comment;
import com.edu.lnu.mongoDbPpoject.model.Post;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.repository.mongoRepository.PostRepository;
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
    public Post addComment(String postId, String text) {
            User user = userService.getCurrentUser();
            Post post = postRepository.getById(postId);

            if (post.getComments() == null) {
                List<Comment> comments = new ArrayList<>();
                post.setComments(comments);
            }

            List<Comment> comments = post.getComments();
            Comment comment = new Comment();
            comment.setAuthor_nickname(user.getNickName());
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

    @Override
    public int countComments(String username) {
        List<Post> posts = postRepository.getAllByAuthorNickName(username);
        int count = 0;
        for (Post s:  posts){
            for (Comment c: s.getComments()){
                count++;
            }
        }
        return count;
    }
}
