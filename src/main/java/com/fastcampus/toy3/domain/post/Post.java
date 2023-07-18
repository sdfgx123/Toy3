package com.fastcampus.toy3.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = CategoryConverter.class)
    private Category category;
    @Column(length = 30)
    private String title;
    @Column(columnDefinition = "text", length = 2000)
    private String content;
    @Lob
    @Column(columnDefinition = "mediumblob", length = 4194304)
    private byte[] thumbnail;
    @Column(length = 10, nullable = false)
    private String userNickname;
    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, insertable = false)
    private LocalDateTime updatedAt;
}
