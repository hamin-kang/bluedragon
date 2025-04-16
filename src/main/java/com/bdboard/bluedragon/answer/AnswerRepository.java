package com.bdboard.bluedragon.answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> { // 답변 데이터베이스 CRUD
	
}