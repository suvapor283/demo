package com.example.basic.domain.auth.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private Long id;
    private String username;
    private String password;
    private String role;
}