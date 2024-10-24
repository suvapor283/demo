package com.example.basic.domain.article.repository;

import com.example.basic.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByAuthorIdOrderByRegDateDesc(long memberId);
}
