package com.example.basic.domain.auth.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.service.MemberService;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.service.CommentService;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final ReqResHandler reqResHandler;
    private final MemberService memberService;
    private final ArticleService articleService;
    private final CommentService commentService;

    // 회원가입
    @GetMapping("/join")
    public String memberjoin() {

        return "join";
    }

    @Getter
    @Setter
    public static class Joinform {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        private String role;
    }

    @PostMapping("/join")
    public String join(@Valid Joinform joinform) {
        memberService.join(joinform.username, joinform.password, joinform.role);

        return "redirect:/article/list";
    }

    // 로그인
    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @Getter
    @Setter
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm) {

        Member targetMember = memberService.loginCheck(loginForm.username);

        if (targetMember == null || !targetMember.getPassword().equals(loginForm.password)) {
            return "login-fail";
        }

        reqResHandler.setLoginMember(targetMember);

        return "redirect:/article/list";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/article/list";
    }

    // 마이 페이지
    @RequestMapping("/myPage")
    public String myPage(Model model) {
        Member loginMember = reqResHandler.getLoginMember();

        List<Article> myArticleList = articleService.getByMemberId(loginMember.getId());
        List<Comment> myCommentList = commentService.getByMemberId(loginMember.getId());
        model.addAttribute("myArticleList", myArticleList);
        model.addAttribute("myCommentList", myCommentList);

        return "myPage";
    }
}