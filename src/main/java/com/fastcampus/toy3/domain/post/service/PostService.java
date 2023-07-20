package com.fastcampus.toy3.domain.post.service;

import com.fastcampus.toy3.domain.User;
import com.fastcampus.toy3.domain.post.Post;
import com.fastcampus.toy3.domain.post.dto.PostWriteForm;
import com.fastcampus.toy3.domain.post.file.FileManager;
import com.fastcampus.toy3.domain.post.file.UploadedFile;
import com.fastcampus.toy3.domain.post.report.Report;
import com.fastcampus.toy3.domain.post.report.ReportReason;
import com.fastcampus.toy3.domain.post.report.repository.ReportRepository;
import com.fastcampus.toy3.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileManager fileManager;
    private final ReportRepository reportRepository;

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

    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, PostWriteForm form) throws IOException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. postId=" + id));

        post.update(form.getTitle(), form.getContent());

        postRepository.save(post);
    }

    @Transactional
    public void reportPost(Long postId, ReportReason reason, User user) {
        Post post = findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. postId=" + postId));

        Report report = Report.builder()
                .post(post)
                .user(user)
                .reason(reason)
                .build();
        reportRepository.save(report);
    }
}
