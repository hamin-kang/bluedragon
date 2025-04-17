package com.bdboard.bluedragon.comment;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.bdboard.bluedragon.board.Board;
import com.bdboard.bluedragon.board.BoardService;
import com.bdboard.bluedragon.user.SiteUser;
import com.bdboard.bluedragon.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController { // 댓글 관련 웹 요청 처리
	private final BoardService boardService;
	
	private final CommentService commentService;
	
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createComment(Model model, @PathVariable("id") Integer id, 
			@Valid CommentForm commentForm, BindingResult bindingResult, Principal principal) {
		Board board = this.boardService.getBoard(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("board", board);
			return "board_detail";
		}
		Comment comment = this.commentService.create(board, commentForm.getContent(), siteUser);
		
		return String.format("redirect:/board/detail/%s#comment_%s", comment.getBoard().getId(), comment.getId());
	}
	
	@GetMapping("/modify/{id}")
	public String commentModify(CommentForm commentForm, @PathVariable("id") Integer id, Principal principal) {
		Comment comment = this.commentService.getComment(id);
		if (!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		commentForm.setContent(comment.getContent());
		
		return "comment_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String commentModify(@Valid CommentForm commentForm, BindingResult bindingResult,
			@PathVariable("id") Integer id, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "comment_form";
		}
		Comment comment = this.commentService.getComment(id);
		if (!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.commentService.modify(comment, commentForm.getContent());
		
		return String.format("redirect:/board/detail/%s#comment_%s", comment.getBoard().getId(), comment.getId());
	}
	
}