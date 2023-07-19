package com.fastcampus.toy3admin.service;

import com.fastcampus.toy3admin.model.post.Post;
import com.fastcampus.toy3admin.model.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostService")
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostList() {
        return postRepository.findAll();
    }
}
