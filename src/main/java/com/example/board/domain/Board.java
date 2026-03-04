package com.example.board.domain;

import java.time.LocalDateTime;

/**
 * 게시글 데이터 구조 (Entity)
 * - 실제 데이터를 담는 클래스입니다.
 */
public class Board {

    private Long id;           // 게시글 고유 번호
    private String title;      // 제목
    private String content;    // 내용
    private LocalDateTime createdAt; // 생성 시각

    // ─────────────────────────────────────────
    // 생성자 (Constructor)
    // ─────────────────────────────────────────

    public Board() {}

    public Board(Long id, String title, String content, LocalDateTime createdAt) {
        this.id        = id;
        this.title     = title;
        this.content   = content;
        this.createdAt = createdAt;
    }

    // ─────────────────────────────────────────
    // Getter / Setter
    // ─────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}