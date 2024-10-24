package com.example.basic.domain.auth.entity;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Article> articleList;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    public void setRole(String role) {
        this.role = (role == null || role.isEmpty()) ? "normal" : role;
    }

    public String switchKoreanRole() {
        switch (this.role) {
            case "admin":
                return "관리자";

            case "normal":
                return "일반회원";
        }

        throw new RuntimeException("없는 권한 정보입니다.");
    }
}