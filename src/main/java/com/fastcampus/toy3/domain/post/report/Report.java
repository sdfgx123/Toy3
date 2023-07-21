package com.fastcampus.toy3.domain.post.report;

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
@Table(name = "report_tb")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Report(Post post, User user, ReportReason reason) {
        this.post = post;
        this.user = user;
        this.reason = reason;
    }
}