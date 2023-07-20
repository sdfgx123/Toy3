package com.fastcampus.toy3.domain.post.controller;

import com.fastcampus.toy3.domain.User;
import com.fastcampus.toy3.domain.post.Post;
import com.fastcampus.toy3.domain.post.dto.PostWriteForm;
import com.fastcampus.toy3.domain.post.report.ReportReason;
import com.fastcampus.toy3.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/post")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String getIndex() {
        return "post/index";
    }

    @GetMapping("/write")
    public String getWriteForm() {
        return "post/write-form";
    }

    @PostMapping("/write")
    public String write(
            @ModelAttribute PostWriteForm form,
            RedirectAttributes redirectAttributes) throws IOException {

        log.info("title: {}", form.getTitle());
        log.info("content: {}", form.getContent());
        log.info("thumbnail: {}", form.getThumbnail());

        Long id = postService.save(form);

        redirectAttributes.addAttribute("id", id);
        return "redirect:/post/view/{id}";
    }

    @GetMapping("post/view/{id}")
    public String postView(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.postView(id));
        return "post/post-detail";
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId, Authentication authentication) {
        Post post = postService.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. postId=" + postId));

        // 현재 로그인한 사용자 정보를 가져와서 작성자인지 확인 맞는지 모르겠다ㅠ.ㅠ
        String currentUsername = authentication.getName();
        if (!post.getUserNickname().equals(currentUsername)) {
            throw new IllegalStateException("해당 게시글을 삭제할 권한이 없습니다.");
        }

        //게시글 목록으로 redirect
        postService.deleteById(postId);
        return "redirect:/post/list";
    }

    @GetMapping("/{postId}/update")
    public String getUpdateForm(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. postId=" + postId));

        // 현재 로그인한 사용자 정보를 가져와서 작성자인지 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if (!post.getUserNickname().equals(currentUsername)) {
            throw new IllegalStateException("해당 게시글을 수정할 권한이 없습니다.");
        }

        model.addAttribute("post", post);
        return "post/post-update-form";
    }

    @PostMapping("/{postId}/update")
    public String updatePost(@PathVariable Long postId,
                             @ModelAttribute PostWriteForm form) throws IOException {
        // 현재 로그인한 사용자 정보를 가져와서 작성자인지 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Post post = postService.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. postId=" + postId));

        if (!post.getUserNickname().equals(currentUsername)) {
            throw new IllegalStateException("해당 게시글을 수정할 권한이 없습니다.");
        }

        postService.update(postId, form);

        return "redirect:/post/view/" + postId;
    }

    @PostMapping("/reportPost")
    public String reportPost(@RequestParam Long postId, @RequestParam ReportReason reason, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        User currentUser = (User) authentication.getPrincipal();

        postService.reportPost(postId, reason, currentUser);

        return "redirect:/post/view/" + postId;
    }
}
