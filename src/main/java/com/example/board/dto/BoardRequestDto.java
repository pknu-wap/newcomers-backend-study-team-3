package com.example.board.dto;

/**
 * 게시글 생성/수정 요청 시 클라이언트에서 보내는 데이터
 * - Request Body(JSON)를 받아서 담는 그릇입니다.
 *
 * 예시 JSON:
 * {
 *   "title": "첫 번째 글",
 *   "content": "안녕하세요!"
 * }
 */
public class BoardRequestDto {

    private String title;    // 제목
    private String content;  // 내용

    // 기본 생성자 (Jackson 라이브러리가 JSON → 객체 변환 시 필요)
    public BoardRequestDto() {}

    public BoardRequestDto(String title, String content) {
        this.title   = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}