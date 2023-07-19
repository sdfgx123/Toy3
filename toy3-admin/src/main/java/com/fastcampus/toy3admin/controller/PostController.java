package com.fastcampus.toy3admin.controller;

import com.fastcampus.toy3admin.model.post.Post;
import com.fastcampus.toy3admin.model.post.PostRepository;
import com.fastcampus.toy3admin.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @ResponseBody
    @RequestMapping("postList")
    public List<Post> getAllPosts(Model model) throws Exception {
        log.info(this.getClass().getName() + "postList start");
        List<Post> posts = postService.getPostList();

        if (posts == null) {
            posts = new ArrayList<Post>();
        }

        model.addAttribute("posts", posts);

        log.info(this.getClass().getName() + "postList end");

        return posts;
    }

    @RequestMapping("deletePost")
    public String deletePost() {
        return "";
    }
}
