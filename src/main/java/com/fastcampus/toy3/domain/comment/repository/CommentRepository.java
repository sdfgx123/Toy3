package com.fastcampus.toy3.domain.comment.repository;

import com.fastcampus.toy3.domain.comment.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    void deleteById(Long id);
    List<Comment> findByReplyId(Long commentId);
}
