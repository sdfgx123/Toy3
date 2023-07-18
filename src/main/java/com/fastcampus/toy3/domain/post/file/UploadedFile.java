package com.fastcampus.toy3.domain.post.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadedFile {

    private final String uploadedName;
    private final String storedName;
}
