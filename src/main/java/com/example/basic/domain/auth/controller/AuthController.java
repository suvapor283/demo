package com.example.basic.domain.auth.controller;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.service.MemberService;
import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final ReqResHandler reqResHandler;

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
    public String login(@Valid LoginForm loginForm, HttpServletResponse response, HttpSession session) {

        Member targetMember = memberService.loginCheck(loginForm.username);

        if (targetMember == null || !targetMember.getPassword().equals(loginForm.password)) {
            return "login-fail";
        }

        session.setAttribute("loginUser", targetMember);

        return "redirect:/article/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/article/list";
    }

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
    public String join(Joinform joinform, Member member) {
        memberService.join(joinform.username, joinform.password, joinform.role);

        return "redirect:/article/list";
    }
}