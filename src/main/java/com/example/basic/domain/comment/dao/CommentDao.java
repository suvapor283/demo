package com.example.basic.domain.comment.dao;

import com.example.basic.domain.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    void save(Comment comment);
    List<Comment> findByArticleId(Long articleId);
}