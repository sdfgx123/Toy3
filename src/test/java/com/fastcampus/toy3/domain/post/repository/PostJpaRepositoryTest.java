package com.fastcampus.toy3.domain.post.repository;

import com.fastcampus.toy3.domain.post.Category;
import com.fastcampus.toy3.domain.post.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostJpaRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("게시글 추가")
    void save() {
        Post post = Post.builder()
                .category(Category.NEWBIE)
                .title("첫 게시글 입니다.")
                .content("안녕하세요, 반갑습니다. 초심자입니다.")
                .userNickname("초심자")
                .build();

        Post savedPost = postRepository.save(post);

        assertThat(savedPost).isSameAs(post);
        assertThat(savedPost.getId()).isNotNull();
    }

}