package com.edu.lnu.mongoDbPpoject.service;

import com.edu.lnu.mongoDbPpoject.model.Comment;

public interface CommentService {
    Comment addComment(String  postId, String text);
    Comment deleteComment(String postId, String commentId);
    Comment editComment(String commentId, String postId, String text);
}
