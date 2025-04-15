package com.bdboard.bluedragon.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm { // 댓글 폼 데이터 검증
	@NotEmpty(message = "내용은 필수 입력 항목입니다.")
	private String content;
}
