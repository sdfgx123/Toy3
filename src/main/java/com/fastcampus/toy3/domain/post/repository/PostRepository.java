package com.fastcampus.toy3.domain.post.repository;

import com.fastcampus.toy3.domain.post.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);
    void deleteById(Long id);
    Optional<Post> findById(Long id);
}
