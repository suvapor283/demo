package com.example.basic.domain.article.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 1. 기능 구현
    // 2. 유지 보수를 생각한 코드
    public Article getById(long id) {
        Optional<Article> articleOpt = articleRepository.findById(id);

        return articleOpt.get();
    }

    public List<Article> getAll() {

        return articleRepository.findAll();
    }

    public void write(String title, String body) {
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);
    }

    public void deleteById(long id) {

        articleRepository.deleteById(id);
    }

    public void update(long id, String title, String body) {
        Optional<Article> articleOpt = articleRepository.findById(id);
        Article article = articleOpt.get();

        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);
    }
}