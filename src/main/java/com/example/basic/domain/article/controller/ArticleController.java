package com.example.basic.domain.article.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ReqResHandler reqResHandler;

    @RequestMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        Article article = articleService.getById(id);

        model.addAttribute("article", article);

        return "article/detail";
    }

    @RequestMapping("/article/list")
    public String list(Model model) {
        List<Article> articleList = articleService.getAll();

        model.addAttribute("articleList", articleList);

        return "article/list";
    }

    @GetMapping("/article/write")
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

    @PostMapping("/article/write")
    public String write(@Valid WriteForm writeForm, HttpSession session) {
        articleService.write(writeForm.title, writeForm.body, (Member) session.getAttribute("loginUser"));

        return "redirect:/article/list"; // redirect 뒤에 적는 것은 url을 적는 것. 템플릿 이름 아님. 주소창을 해당 url로 바꾸라는 의미
    }

    @RequestMapping("/article/delete/{id}")
    public String delete(@PathVariable long id) {
        articleService.deleteById(id);

        return "redirect:/article/list";
    }

    @Getter
    @Setter
    public static class ModifyForm {
        @NotBlank
        String title;
        @NotBlank
        String body;
    }

    @RequestMapping("/article/modify/{id}")
    public String modify(@PathVariable("id") long id, @Valid ModifyForm modifyForm) {
        articleService.update(id, modifyForm.getTitle(), modifyForm.getBody());

        return "redirect:/article/detail/%d".formatted(id); // 브라우저 출력 => html 문자열로 출력
    }
}