package com.bdboard.bluedragon.comment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bdboard.bluedragon.DataNotFoundException;
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
	
	public Comment getComment(Integer id) {
		Optional<Comment> comment = this.commentRepository.findById(id);
		if (comment.isPresent()) {
			return comment.get();
		} else {
			throw new DataNotFoundException("comment not found");
		}
	}
	
	public void modify(Comment comment, String content) {
		comment.setContent(content);
		comment.setModifyDate(LocalDateTime.now());
		this.commentRepository.save(comment);
	}
	
	public void delete(Comment comment) {
		this.commentRepository.delete(comment);
	}
	
	public void vote(Comment comment, SiteUser siteUser) {
		comment.getVoter().add(siteUser);
		this.commentRepository.save(comment);
	}
	
}