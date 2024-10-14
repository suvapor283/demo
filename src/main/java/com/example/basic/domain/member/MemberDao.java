package com.example.basic.domain.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    void save(Member member);
    Member loginCheck(String username);
}