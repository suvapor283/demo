package com.example.basic;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import com.example.basic.domain.auth.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringBasicApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장 - save")
    void test1() {
        Member member = Member.builder()
                .username("kim")
                .password("1234")
                .role("admin")
                .build();

        memberRepository.save(member);

        Member member2 = Member.builder()
                .username("hong")
                .password("1234")
                .role("normal")
                .build();

        memberRepository.save(member2);
    }

    @Test
    @DisplayName("회원 전체 조회 - findAll")
    void test2() {
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            System.out.println(member.getUsername());
        }
    }

    @Test
    @DisplayName("회원 단건 조회 - findById")
    void test3() {
        Optional<Member> memberOpt = memberRepository.findById(1L);

        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();

            System.out.println(member.getUsername());
            System.out.println(member.getPassword());
            System.out.println(member.getRole());
        }
    }

    @Test
    @DisplayName("회원 삭제 - delete, deleteById")
    void test4() {
        // 아이디로 삭제
        memberRepository.deleteById(1L);

        // 엔터티로 삭제
        Optional<Member> memberOpt = memberRepository.findById(1L);
        Member member = memberOpt.get();

        memberRepository.delete(member);
    }

    @Test
    @DisplayName("회원 수정 - save")
    void test5() {
        // 수정할 대상 먼저 찾아오기
        Optional<Member> memberOpt = memberRepository.findById(1L);
        Member member = memberOpt.get();

        // 엔터티 값 변경
        member.setRole("admin");
        // 저장
        memberRepository.save(member);
    }
}