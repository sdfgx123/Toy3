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

import javax.persistence.Id;
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

    @ResponseBody
    @RequestMapping("deletePost")
    public int deletePost(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + "deletePost started");

        String id = request.getParameter("id");
        log.info("post number : " + id);

        int res = 0;

        String message = "";
        String url = "/postList";

        res = postService.deletePost(id);

        if (res > 0) message = "Deleting post just succeeded.";
        message = "Deleting post just have failed. Please retry the process.";

        model.addAttribute("message", message);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "deletePost ended");

        return res;
    }
}
