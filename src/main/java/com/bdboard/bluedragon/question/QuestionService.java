package com.bdboard.bluedragon.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdboard.bluedragon.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 데이터 처리를 위해 작성하는 클래스. 리포지터리의 메서드 호출하는 클래스 
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}
}
