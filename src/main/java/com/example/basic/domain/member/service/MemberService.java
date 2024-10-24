package com.example.basic.domain.member.service;

import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    public void join(String username, String password, String role) {
//        Member member = new Member();
//        member.setUsername(username);
//        member.setPassword(password);
//        member.setRole(role);
//
//        memberRepository.save(member);
//    }

    public Member getByUsernameOrNull(String username) {
        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if(memberOptional.isEmpty()){
            return null;
        }

        return memberOptional.get();
    }
}