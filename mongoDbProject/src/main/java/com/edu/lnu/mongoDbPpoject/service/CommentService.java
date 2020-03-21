package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Comment;
import com.edu.lnu.mongoDbPpoject.model.Post;

public interface CommentService {
    Post addComment(String postId, String text, String username);
    Comment deleteComment(String postId, String commentId);
    Comment editComment(String commentId, String postId, String text);
    int countComments(String username);
}
