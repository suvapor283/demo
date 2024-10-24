package com.example.basic.domain.comment.controller;

import com.example.basic.domain.article.controller.ArticleController;
import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.comment.service.CommentService;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comment")
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ArticleService articleService;
    private final ReqResHandler reqResHandler;

    // 댓글 작성
    @GetMapping("/write")
    public String commentWrite() {

        return "comment/write";
    }

    @Getter
    @Setter
    public static class WriteForm {
        private long articleId;
        @NotBlank
        private String body;
    }

    @PostMapping("/write")
    public String write(@Valid WriteForm writeForm) {
        Article article = articleService.getById(writeForm.articleId);
        Member loginMember = reqResHandler.getLoginMember();

        commentService.write(writeForm.body, article, loginMember);

        return "redirect:/article/detail/%d".formatted(writeForm.articleId);
    }

    // 댓글 수정
    @Getter
    @Setter
    public static class ModifyForm {
        private long articleId;
        @NotBlank
        private String body;
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") long id, @Valid ModifyForm modifyForm) {
        commentService.update(id, modifyForm.body);

        return "redirect:/article/detail/%d".formatted(modifyForm.articleId);
    }

    // 댓글 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, long articleId) {
        commentService.deleteById(id);

        return "redirect:/article/detail/%d".formatted(articleId);
    }
}