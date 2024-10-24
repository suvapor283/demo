package com.example.basic.domain.auth.service;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(String username, String password, String role) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setRole(role);

        memberRepository.save(member);
    }

    public Member loginCheck(String username) {

        return memberRepository.findByUsername(username);
    }
}