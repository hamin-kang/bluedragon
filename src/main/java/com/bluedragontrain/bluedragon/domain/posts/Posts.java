package com.bluedragontrain.bluedragon.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본생성자
@Entity // 테이블과 링크될 클래스
public class Posts {
    @Id // @Id: 해당 테이블의 PK를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue: PK 생성 규칙,
    // GenerationType.IDENTITY 옵션 추가해야만 auto_increment 가 된다.
    private Long id;

    @Column(length = 500, nullable = false) // @Column: 테이블의 컬럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함된다.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
