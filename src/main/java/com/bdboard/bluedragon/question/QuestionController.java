package com.bdboard.bluedragon.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 또는 @NonNull 필드에 대해 생성자를 자동으로 생성해줌.
@Controller
public class QuestionController {
	private final QuestionService questionService;
	
	@GetMapping("/question/list")
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}
}