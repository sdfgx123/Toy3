package com.fastcampus.toy3admin.model.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Post {

    private Long id;
    private String category;
    private String title;
    private String content;
    private String uploadedName;
    private String storedName;
    private String userNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Post(String category, String title, String content, String uploadedName, String storedName, String userNickname) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.uploadedName = uploadedName;
        this.storedName = storedName;
        this.userNickname = userNickname;
    }
}
