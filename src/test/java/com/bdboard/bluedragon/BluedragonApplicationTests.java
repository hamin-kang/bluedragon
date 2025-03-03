package com.bdboard.bluedragon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bdboard.bluedragon.answer.Answer;
import com.bdboard.bluedragon.answer.AnswerRepository;
import com.bdboard.bluedragon.question.Question;
import com.bdboard.bluedragon.question.QuestionRepository;

@SpringBootTest
class BluedragonApplicationTests {
	
	@Autowired // DI(Dependency Injection): 의존성 주입, QuestionRepositroy 객체 주입.
	private QuestionRepository questionRepository;
	
	@Autowired // 답변 엔티티 데이터 생성 및 저장.
	private AnswerRepository answerRepository;
	
	@Transactional
	@Test
	void testJpa() { // 질문 데이터에서 답변 데이터 찾기
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}
