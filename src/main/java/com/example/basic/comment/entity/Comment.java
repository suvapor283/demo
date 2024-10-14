package com.example.basic.comment.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Long id;
    private Long articleId;
    private String comm;
}