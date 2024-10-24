package com.example.basic.domain.article.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ReqResHandler reqResHandler;

    // 게시물 작성
    @GetMapping("/write")
    public String articleWrite() {

        return "article/write";
    }

    @Getter
    @Setter
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PostMapping("/write")
    public String write(@Valid WriteForm writeForm) {
        Member loginMember = reqResHandler.getLoginMember();

        articleService.write(writeForm.title, writeForm.body, loginMember);

        return "redirect:/article/list";
    }

    // 게시물 전체 조회
    @RequestMapping("/list")
    public String list(Model model) {
        List<Article> articleList = articleService.getAll();

        model.addAttribute("articleList", articleList);

        return "article/list";
    }

    // 게시물 단건 조회
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        Article article = articleService.getById(id);

        model.addAttribute("article", article);

        return "article/detail";
    }

    // 게시물 수정
    @Getter
    @Setter
    public static class ModifyForm {
        @NotBlank
        String title;
        @NotBlank
        String body;
    }

    @RequestMapping("/modify/{id}")
    public String modify(@PathVariable("id") long id, @Valid ModifyForm modifyForm) {
        articleService.update(id, modifyForm.getTitle(), modifyForm.getBody());

        return "redirect:/article/detail/%d".formatted(id);
    }

    // 게시물 삭제
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        articleService.deleteById(id);

        return "redirect:/article/list";
    }
}