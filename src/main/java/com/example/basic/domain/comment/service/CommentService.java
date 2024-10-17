package com.example.basic.domain.comment.service;

import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void write(String content) {
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Comment comment = Comment.builder()
                .content(content)
                .build();

        commentRepository.save(comment);
    }

//    public List<Comment> getByArticleId(long articleId) {
//
//        return commentRepository.findByArticleId(articleId);
//    }
}