package com.example.basic.domain.member;

import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class LoginController {

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
    public String login(@Valid LoginForm loginForm, HttpServletResponse response) {

        Member member = memberService.loginCheck(loginForm.username);

        String dbrole = "admin"; // normal, admin

        if (member == null || !member.getPassword().equals(loginForm.password)) {
            return "login-fail";
        }

        Cookie cookie = new Cookie("loginUser", loginForm.username);
        Cookie role = new Cookie("role", dbrole);

        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);
        response.addCookie(role);

        return "redirect:/article/list";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie targetCookie = reqResHandler.getCookieByName(request, "loginUser");

        if (targetCookie != null) {
            targetCookie.setMaxAge(0);
            response.addCookie(targetCookie);
        }

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
        private String password;
    }

    @PostMapping("/join")
    public String join(Joinform joinform) {
        memberService.join(joinform.username, joinform.password);

        return "redirect:/article/list";
    }
}