package com.bdboard.bluedragon.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bdboard.bluedragon.comment.Comment;
import com.bdboard.bluedragon.user.SiteUser;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	private Specification<Board> search(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<Board, SiteUser> u1 = b.join("author", JoinType.LEFT);
				Join<Board, Comment> c = b.join("commentList", JoinType.LEFT);
				Join<Comment, SiteUser> u2 = c.join("author", JoinType.LEFT);
				return cb.or(cb.like(b.get("subject"), "%" + kw + "%"), // 제목
						cb.like(b.get("content"), "%" + kw + "%"), // 내용
						cb.like(u1.get("username"), "%" + kw + "%"), // 질문 작성자
						cb.like(c.get("content"), "%" + kw + "%"), // 답글 내용
						cb.like(u2.get("username"), "%" + kw + "%")); // 답글 작성자
			}
		};
	}
	
	public Page<Board> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Specification<Board> spec = search(kw);
	}
}
