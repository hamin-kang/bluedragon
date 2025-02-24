package com.bdboard.bluedragon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BluedragonApplicationTests {
	
	@Autowired // DI(Dependency Injection): 의존성 주입, QuestionRepositroy 객체 주입.
	private QuestionRepository questionRepository;
	
	@Test
	void testJpa() {
		List<Question> qList = this.questionRepository.findBySubjectLike("트럼프%");
		Question q = qList.get(0);
		assertEquals("트럼프 2기의 무역정책", q.getSubject());
	}
}
