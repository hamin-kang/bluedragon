package com.bdboard.bluedragon.comment;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.bdboard.bluedragon.board.Board;
import com.bdboard.bluedragon.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	public Comment create(Board board, String content, SiteUser author) {
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setCreateDate(LocalDateTime.now());
		comment.setBoard(board);
		comment.setAuthor(author);
		this.commentRepository.save(comment);
		
		return comment;
	}
}