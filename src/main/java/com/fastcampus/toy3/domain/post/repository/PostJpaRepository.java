package com.fastcampus.toy3.domain.post.repository;

import com.fastcampus.toy3.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class PostJpaRepository implements PostRepository {

    private final EntityManager em;

    @Transactional
    public Post save(Post post) {
        em.persist(post);
        return post;
    }
}
