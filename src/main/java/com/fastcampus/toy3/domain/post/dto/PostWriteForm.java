package com.fastcampus.toy3.domain.post.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostWriteForm {

    @NotEmpty
    private String title;
    private String content;
    private MultipartFile thumbnail;
}
