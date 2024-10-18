package com.example.basic.domain.comment.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.service.CommentService;
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
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/write")
    public String commentWrite() {

        return "comment/write";
    }

    @Getter
    @Setter
    public static class WriteForm {
        private long articleId;
        @NotBlank
        private String comm;
    }

    @PostMapping("/comment/write")
    public String write(@Valid WriteForm writeForm) {

        commentService.write(writeForm.comm, writeForm.articleId);
        return "redirect:/article/detail/%d".formatted(writeForm.articleId);
    }

//    @RequestMapping("/comment/list/{id}")
//    public String list(@PathVariable("id") long articleId, Model model) {
//        List<Comment> commentList = commentService.getByArticleId(articleId);
//
//        model.addAttribute("commentList", commentList);
//
//        return "article/detail";
//    }
}