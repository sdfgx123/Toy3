package com.fastcampus.toy3.domain.comment;

import com.fastcampus.toy3.domain.comment.dto.CommentRequestDto;
import com.fastcampus.toy3.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long saveComment(CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getDepth() == 0) {
            return commentRepository.save(commentRequestDto.toEntity()).getId();
        }
        throw new IllegalArgumentException("댓글을 달 수 없습니다.");
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. commentId=" + commentId));

        if (comment.getDepth() == 0) {
            List<Comment> childComments = commentRepository.findByReplyId(commentId);
            for (Comment childComment : childComments) {
                commentRepository.deleteById(childComment.getId());
            }
        }

        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void saveReply(CommentRequestDto commentRequestDto, String currentUsername, Long commentId) {
        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. commentId=" + commentId));

        Comment replyComment = Comment.builder()
                .content(commentRequestDto.getContent())
                .nickName(currentUsername)
                .depth(1)
                .replyId(commentId)
                .post(parentComment.getPost())
                .build();

        commentRepository.save(replyComment);
    }
}
