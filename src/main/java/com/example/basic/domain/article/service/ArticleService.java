package com.example.basic.domain.article.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.auth.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void write(String title, String body, Member loginMember) {
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        article.setAuthor(loginMember);
        article.setRegDate(LocalDateTime.now());

        articleRepository.save(article);
    }

    public List<Article> getAll() {

        return articleRepository.findAll();
    }

    public Article getById(long id) {
        Optional<Article> articleOpt = articleRepository.findById(id);

        if (articleOpt.isEmpty()) {
            throw new RuntimeException("존재하지 않는 게시물입니다.");
        }

        return articleOpt.get();
    }

    public void update(long id, String title, String body) {
        Article article = this.getById(id);
        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);
    }

    public void deleteById(long id) {
        Article article = this.getById(id);

        articleRepository.deleteById(article.getId());
    }

    public List<Article> getByMemberId(long authorId) {

        return articleRepository.findByAuthorIdOrderByRegDateDesc(authorId);
    }
}