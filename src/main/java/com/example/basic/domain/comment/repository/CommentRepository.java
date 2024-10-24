package com.example.basic.domain.comment.repository;

import com.example.basic.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByAuthorIdOrderByRegDateDesc(long authorId);
}