package com.fastcampus.toy3.domain.post.controller;

import com.fastcampus.toy3.domain.post.dto.PostWriteForm;
import com.fastcampus.toy3.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
