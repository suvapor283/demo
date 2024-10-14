package com.example.basic.domain.comment.service;

import com.example.basic.domain.comment.dao.CommentDao;
import com.example.basic.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;

    public void write(long articleId, String comm) {
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Comment comment = Comment.builder()
                .articleId(articleId)
                .comm(comm)
                .build();

        commentDao.save(comment);
    }

    public List<Comment> getByArticleId(long articleId) {
        return commentDao.findByArticleId(articleId);
    }
}