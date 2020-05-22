package com.example.community.mapper;

import com.example.community.demo.Comment;

public interface CommentExtMapper {
    int addCommentLike(Comment record);
    int findMaxCommentId();
}
