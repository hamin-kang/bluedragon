package com.bluedragontrain.bluedragon.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest { // save, findAll 기능을 테스트
    @Autowired
    PostsRepository postsRepository;

    @AfterEach // junit 에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시물";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 테이블 posts 에 insert/update 쿼리를 실행한다.
                // id 값이 있으면 update, 없으면 insert
                .title(title)
                .content(content)
                .author("hwaminn9185@gmail.com")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts 에 있는 모든 데이터를 조회한다.

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title").content("content").author("author").build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate=" + posts.getCreatedDate() +
                ", modifiedDate=" + posts.getModifiedDate()
        );

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
