package com.bdboard.bluedragon.board;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.bdboard.bluedragon.comment.CommentForm;
import com.bdboard.bluedragon.user.SiteUser;
import com.bdboard.bluedragon.user.UserService;

import jakarta.validation.Valid;
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
		Page<Board> paging = this.boardService.getList(page, kw); // 페이징 및 검색 적용된 게시물 목록 조회
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		return "board_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm) {
		Board board = this.boardService.getBoard(id);
		model.addAttribute("board", board);
		return "board_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String boardCreate(BoardForm boardForm) {
		return "board_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String boardCreate(@Valid BoardForm boardForm, BindingResult bindingResult,
			Principal principal, @RequestParam("file") MultipartFile file) throws Exception {
		if (bindingResult.hasErrors()) {
			return "board_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.boardService.create(boardForm.getSubject(), boardForm.getContent(), siteUser, file);
		return "redirect:/board/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String boardModify(BoardForm boardForm, @PathVariable("id") Integer id, Principal principal) {
		Board board = this.boardService.getBoard(id);
		if (!board.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		boardForm.setSubject(board.getSubject());
		boardForm.setContent(board.getContent());
		
		return "board_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String boardModify(@Valid BoardForm boardForm, BindingResult bindingResult,
			Principal principal, @PathVariable("id") Integer id) {
		if (bindingResult.hasErrors()) {
			return "board_form";
		}
		Board board = this.boardService.getBoard(id);
		if (!board.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.boardService.modify(board, boardForm.getSubject(), boardForm.getContent());
		
		return String.format("redirect:/board/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String boardDelete(Principal principal, @PathVariable("id") Integer id) {
		Board board = this.boardService.getBoard(id);
		if (!board.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.boardService.delete(board);
		
		return "redirect:/";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String boardVote(Principal principal, @PathVariable("id") Integer id) {
		Board board = this.boardService.getBoard(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.boardService.vote(board, siteUser);
		
		return String.format("redirect:/board/detail/%s", id);
	}
	
}