package com.example.board.dto;

import com.example.board.domain.Board;
import java.time.LocalDateTime;

/**
 * 게시글 조회 결과를 클라이언트에게 돌려줄 때 사용하는 데이터
 * - Board(Entity) → BoardResponseDto 로 변환해서 응답합니다.
 * - 외부에 노출하고 싶지 않은 필드를 숨길 수 있습니다.
 */
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // ─────────────────────────────────────────
    // Board(Entity)를 받아서 DTO를 만드는 정적 팩토리 메서드
    // ─────────────────────────────────────────
    public static BoardResponseDto from(Board board) {
        BoardResponseDto dto = new BoardResponseDto();
        dto.id        = board.getId();
        dto.title     = board.getTitle();
        dto.content   = board.getContent();
        dto.createdAt = board.getCreatedAt();
        return dto;
    }

    // ─────────────────────────────────────────
    // Getter
    // ─────────────────────────────────────────
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}