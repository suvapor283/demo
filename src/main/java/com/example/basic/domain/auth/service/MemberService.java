package com.example.basic.domain.auth.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(String username, String password, String role) {
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Member member = Member.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        memberRepository.save(member);
    }

    public Member loginCheck(String username) {

        return memberRepository.findByUsername(username);
    }
}