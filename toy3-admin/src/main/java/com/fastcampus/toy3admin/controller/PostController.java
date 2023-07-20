package com.fastcampus.toy3admin.controller;

import com.fastcampus.toy3admin.model.post.Post;
import com.fastcampus.toy3admin.model.post.PostRepository;
import com.fastcampus.toy3admin.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @RequestMapping("/postList")
    public String getAllPosts(Model model) throws Exception {
        log.info(this.getClass().getName() + "postList start");
        List<Post> posts = postService.getPostList();

        if (posts == null) {
            posts = new ArrayList<Post>();
        }

        model.addAttribute("posts", posts);

        log.info(this.getClass().getName() + "postList end");

        return "postList";
    }

    @RequestMapping("/deletePost")
    public String deletePost(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + "deletePost start");

        String id = request.getParameter("id");
        log.info("post number : " + id);

        int res = 0;

        String message = "";
        String url = "/postList";

        res = postService.deletePost(id);
        log.info("res : " + res);

        if (res > 0) {
            message = "Deleting post task succeeded.";
        } else {
            message = "Deleting post process have failed. Please retry the process.";
        }

        model.addAttribute("message", message);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "deletePost end");

        return "redirect";
    }

    @RequestMapping("/")
    public String main() {
        return "index";
    }
}
