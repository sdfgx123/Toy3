package com.fastcampus.toy3admin.model.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {

    public List<Post> findAll();

    public int deleteById();

}
