package com.example.basic.comment.dao;

import com.example.basic.article.entity.Article;
import com.example.basic.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    void save(Comment comment);
    List<Comment> findByArticleId(Long articleId);
}