package com.fastcampus.toy3admin.model.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {

    List<Post> findAll();

    int deleteById(String id);

}
