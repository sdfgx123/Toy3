package com.fastcampus.toy3.domain.comment;

import com.fastcampus.toy3.domain.comment.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    @ResponseBody
    public Long saveComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.saveComment(commentRequestDto);
    }

    @PostMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId,
                                @RequestParam("postId") Long postId){
        commentService.deleteComment(commentId);
        return "redirect:/post/" + postId;
    }

    @PostMapping("/{commentId}/saveReply")
    public String saveReply(@PathVariable Long commentId,
                            @ModelAttribute CommentRequestDto commentRequestDto, Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String currentUsername = authentication.getName();
        commentService.saveReply(commentRequestDto, currentUsername, commentId);

        return "redirect:/post/view/" + commentRequestDto.getPostId();
    }
}
