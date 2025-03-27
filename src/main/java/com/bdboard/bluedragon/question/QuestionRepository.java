package com.bdboard.bluedragon.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> { // Repository: 데이터 접근 로직 담당
	// JpaRepository<Question, Integer>: JPA 기능을 사용하여 Question 엔티티에 대한 CRUD 연산을 제공한다.
	// Question 엔티티로 Repository 생성. 이 엔티티의 기본키가 Integer
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable);
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}