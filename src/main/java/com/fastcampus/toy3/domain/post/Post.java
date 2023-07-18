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
    private String uploadedName;
    private String storedName;
    @Column(length = 10, nullable = false)
    private String userNickname;
    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, insertable = false)
    private LocalDateTime updatedAt;

    public Post(String title, String content, String uploadedName, String storedName) {
        this.category = Category.NEWBIE;
        this.title = title;
        this.content = content;
        this.uploadedName = uploadedName;
        this.storedName = storedName;
        this.userNickname = "임시사용자01";
    }
}
