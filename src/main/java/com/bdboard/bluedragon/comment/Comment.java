package com.bdboard.bluedragon.comment;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
	private Integer id;
	
	private String content;
	
	private LocalDateTime modifyDate;
}