package com.bdboard.bluedragon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bdboard.bluedragon.question.QuestionService;

@SpringBootTest
class BluedragonApplicationTests {
	@Autowired
	private QuestionService questionService;
	
	@Test
	void testJpa() {
		for (int i = 0; i < 300; i++) {
			String subject = String.format("하나 둘 셋 - 테스트 데이터입니다: [%03d]", i + 1);
			String content = "상처를 치료해 줄 사람 어디 없나~ 가만히 놔두다간 끊임없이 덧나.";
			this.questionService.create(subject, content, null);
		}
	}
}
