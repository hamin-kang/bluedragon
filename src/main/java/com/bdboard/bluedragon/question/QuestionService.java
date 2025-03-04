package com.bdboard.bluedragon.question;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 데이터 처리를 위해 작성하는 클래스. 리포지터리의 메서드 호출하는 클래스 
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}
}
