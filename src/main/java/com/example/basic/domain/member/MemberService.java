package com.example.basic.domain.member;

import com.example.basic.domain.article.dao.ArticleDao;
import com.example.basic.domain.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    public void join(String username, String password) {
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Member member = Member.builder()
                .username(username)
                .password(password)
                .build();

        memberDao.save(member);
    }

    public Member loginCheck(String username) {

        return memberDao.loginCheck(username);
    }
}