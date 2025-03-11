package com.bdboard.bluedragon.answer;

import java.time.LocalDateTime;

import com.bdboard.bluedragon.question.Question;
import com.bdboard.bluedragon.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer { // 답변 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content; // 답변 내용
	
	private LocalDateTime createDate; // 답변 작성 시각
	
	@ManyToOne // 답변으로 질문 참조. 질문 하나에 답변이 여러 개 달릴 수 있으므로 N:1(다대일)
	private Question question; // 질문 데이터
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
}