package com.fastcampus.toy3.domain.post.service;

import com.fastcampus.toy3.domain.post.Post;
import com.fastcampus.toy3.domain.post.dto.PostWriteForm;
import com.fastcampus.toy3.domain.post.file.FileManager;
import com.fastcampus.toy3.domain.post.file.UploadedFile;
import com.fastcampus.toy3.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileManager fileManager;

    @Transactional
    public Long save(PostWriteForm form) throws IOException {
        UploadedFile uploadedFile = fileManager.storeFile(form.getThumbnail());

        Post post = postRepository.save(
                new Post(form.getTitle(),
                        form.getContent(),
                        uploadedFile.getUploadedName(),
                        uploadedFile.getStoredName()));

        return post.getId();
    }

    @Transactional
    public Post postView(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. postId=" + id));
    }
}
