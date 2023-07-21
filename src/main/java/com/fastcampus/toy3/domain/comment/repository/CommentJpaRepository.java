package com.fastcampus.toy3.domain.comment.repository;

import com.fastcampus.toy3.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentJpaRepository implements CommentRepository{

    private final EntityManager em;

    @Transactional
    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        Comment comment = em.find(Comment.class, id);
        if (comment != null) {
            em.remove(comment);
        }
    }

    @Transactional
    public List<Comment> findByReplyId(Long commentId) {
        return em.createQuery("select c from Comment c where c.replyId = :commentId", Comment.class)
                .setParameter("commentId", commentId)
                .getResultList();
    }
}
