package com.example.basic;

import com.example.basic.domain.auth.dao.MemberDao;
import com.example.basic.domain.auth.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBasicApplicationTests {

    @Autowired
    private MemberDao memberDao;

    @Test
    void test() {
        Member member = Member.builder()
                .username("444")
                .password("444")
                .role("normal")
                .build();

        memberDao.save(member);
    }
}
