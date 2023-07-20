package com.fastcampus.toy3.domain.comment.dto;

import com.fastcampus.toy3.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String content;
    private String nickName;
    private Integer depth;
    private Long replyId;
    private Long postId;

    @Builder
    public CommentRequestDto(String content, String nickName, Integer depth, Long replyId){
        this.content = content;
        this.nickName = nickName;
        this.depth = depth;
        this.replyId = replyId;
    }

    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .nickName(nickName)
                .depth(depth)
                .replyId(replyId)
                .build();
    }
}
