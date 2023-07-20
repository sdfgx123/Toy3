package com.fastcampus.toy3.domain.post.repository;

import com.fastcampus.toy3.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostJpaRepository implements PostRepository {

    private final EntityManager em;

    @Transactional
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Transactional
    public void deleteById(Long id){
        Post post = em.find(Post.class, id);
        if (post != null) {
            em.remove(post);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(em.find(Post.class, id));
    }
}
