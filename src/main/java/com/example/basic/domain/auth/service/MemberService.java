package com.example.basic.domain.auth.service;

import com.example.basic.domain.auth.dao.MemberDao;
import com.example.basic.domain.auth.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    public void join(String username, String password, String role) {
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Member member = Member.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        memberDao.save(member);
    }

    public Member loginCheck(String username) {

        return memberDao.loginCheck(username);
    }
}