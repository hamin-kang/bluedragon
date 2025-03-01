package com.bdboard.bluedragon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BluedragonApplicationTests {
	
	@Autowired // DI(Dependency Injection): 의존성 주입, QuestionRepositroy 객체 주입.
	private QuestionRepository questionRepository;
	
	@Autowired // 답변 엔티티 데이터 생성 및 저장.
	private AnswerRepository answerRepository;
	
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q); // 어떤 질문의 답변인지 알기 위해서 Question 객체가 필요
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
}
