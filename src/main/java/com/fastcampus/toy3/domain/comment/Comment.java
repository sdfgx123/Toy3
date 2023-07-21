package com.fastcampus.toy3.domain.comment;

import com.fastcampus.toy3.domain.User;
import com.fastcampus.toy3.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment_tb")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String nickName;
    private Integer depth;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private Long replyId;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Comment(String content, String nickName, Integer depth, Post post, Long replyId) {
        this.content = content;
        this.nickName = nickName;
        this.depth = depth;
        this.replyId = replyId;
    }
}
