package com.bdboard.bluedragon.comment;

import java.time.LocalDateTime;
import java.util.Set;

import com.bdboard.bluedragon.board.Board;
import com.bdboard.bluedragon.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
	@ManyToOne
	private SiteUser author;
	
	@ManyToOne
	private Board board;
	
	@ManyToMany
	Set<SiteUser> voter;
}