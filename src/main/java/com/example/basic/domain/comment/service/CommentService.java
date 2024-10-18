package com.example.basic.domain.comment.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void write(String content, long articleId) {
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setArticleId(articleId);

        commentRepository.save(comment);
    }

//    public List<Comment> getByArticleId(long articleId) {
//
//        return commentRepository.findByArticleId(articleId);
//    }
}