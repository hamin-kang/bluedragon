package com.bdboard.bluedragon.board;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController { // 게시판 관련 웹 요청 처리
	private final BoardService boardService;
	
	private final UserService userService;
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="kw", defaultValue="") String kw) {
		log.info("page:{}, kw:{}", page, kw);
		Page<Board> paging = this.boardService.getList(page, kw);
	}
}
