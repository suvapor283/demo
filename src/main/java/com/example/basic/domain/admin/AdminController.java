package com.example.basic.domain.admin;

import com.example.basic.global.reqres.ReqResHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ReqResHandler reqResHandler;

    @GetMapping("/main")
    public String main() {

        return "admin/main";
    }

    @GetMapping("/stat")
    public String stat() {

        return "admin/stat";
    }

    @GetMapping("/user")
    public String user() {

        return "admin/user";
    }
}