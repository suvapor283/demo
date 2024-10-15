package com.example.basic.domain.comment.controller;

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

        commentService.write(writeForm.articleId, writeForm.comm);
        return "redirect:/article/detail/%d".formatted(writeForm.articleId); // redirect 뒤에 적는 것은 url을 적는 것. 템플릿 이름 아님. 주소창을 해당 url로 바꾸라는 의미
    }

    @RequestMapping("/comment/list/{id}")
    public String list(@PathVariable("id") long articleId, Model model) {
        List<Comment> commentList = commentService.getByArticleId(articleId);

        model.addAttribute("commentList", commentList);

        return "article/detail";
    }
}