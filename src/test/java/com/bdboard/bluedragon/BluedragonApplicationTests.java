package com.bdboard.bluedragon;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BluedragonApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("트럼프 2기의 무역정책");
		q1.setContent("트럼프 2기의 무역정책에 대해 자세히 알려주실 수 있을까요?");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1); // 첫번째 질문 저장
		
		Question q2 = new Question();
		q2.setSubject("스프링 부트");
		q2.setContent("스프링 부트가 뭔지, 어떻게 사용하는지 알려주실 분?!");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}
}
