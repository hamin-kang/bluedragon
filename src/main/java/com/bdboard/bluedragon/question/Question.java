package com.bdboard.bluedragon.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.bdboard.bluedragon.answer.Answer;
import com.bdboard.bluedragon.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity // DB테이블과 매핑되는 자바 클래스
@Getter
@Setter
public class Question { // 질문 엔티티
	@Id // 엔티티에서 각 데이터들을 구분하는 기본키. 중복 x
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터가 생성될 때마다 기본키가 1씩 증가한다.
	private Integer id;
	
	// @Column annotation 을 사용하지 않더라도 테이블의 컬럼으로 인식한다. 다만, 세부 설정할 때는 사용
	@Column(length = 200) // 컬럼의 길이 200
	private String subject; // 질문 제목
	
	@Column(columnDefinition = "TEXT") // 글자 수 제한 없는 텍스트
	private String content; // 질문 내용
	
	private LocalDateTime createDate; // 질문 데이터를 작성한 시각
	
	private LocalDateTime modifyDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // 질문에서 답변 참조. 1:N(일대다)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;

	@ManyToMany
	Set<SiteUser> voter;
}
