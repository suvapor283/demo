package com.example.basic.domain.comment.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void write(String body, Article article, Member loginMember) {
        Comment comment = new Comment();
        comment.setBody(body);
        comment.setArticle(article);
        comment.setAuthor(loginMember);
        comment.setRegDate(LocalDateTime.now());

        commentRepository.save(comment);
    }

    public Comment getById(long id) {
        Optional<Comment> commentOpt = commentRepository.findById(id);

        if (commentOpt.isEmpty()) {
            throw new RuntimeException("존재하지 않는 댓글입니다.");
        }

        return commentOpt.get();
    }

    public void update(long id, String body) {
        Comment comment = this.getById(id);
        comment.setBody(body);

        commentRepository.save(comment);
    }

    public void deleteById(long id) {
        Comment comment = this.getById(id);

        commentRepository.deleteById(comment.getId());
    }

    public List<Comment> getByMemberId(long authorId) {

        return commentRepository.findByAuthorIdOrderByRegDateDesc(authorId);
    }
}