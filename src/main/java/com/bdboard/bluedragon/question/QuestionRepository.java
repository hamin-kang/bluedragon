package com.bdboard.bluedragon.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	// Repository 는 생성된 테이블의 데이터를 CRUD 할 수 있게 만들어주는 인터페이스
	// Question 엔티티로 Repository 생성. 이 엔티티의 기본키가 Integer
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
}