package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Article {
    @Id
    @Getter
    @Setter
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
