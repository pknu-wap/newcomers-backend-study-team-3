package com.example.demo.entity;

import com.example.demo.dto.CreateArticleRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static Article from(CreateArticleRequest request) {
        return Article.builder().title(request.getTitle()).content(request.getContent()).createdAt(LocalDateTime.now()).build();
    }
}
