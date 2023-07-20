package com.fastcampus.toy3.domain.comment.dto;

import com.fastcampus.toy3.domain.comment.Comment;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String nickName;
    private Integer depth;
    private Long replyId;
    private Timestamp createdAt;

    public CommentResponseDto(Comment entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.nickName = entity.getNickName();
        this.depth = entity.getDepth();
        this.replyId = entity.getReplyId();
        this.createdAt = entity.getCreatedAt();
    }
}

