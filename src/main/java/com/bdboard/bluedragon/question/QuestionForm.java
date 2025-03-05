package com.bdboard.bluedragon.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm { // 사용자 입력 값 검증하는데 필요한 클래스
	@NotEmpty(message="제목은 필수 입력 항목입니다.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="내용은 필수 입력 항목입니다.")
	private String content;
}
