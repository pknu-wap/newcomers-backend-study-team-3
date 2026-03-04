package com.example.board.controller;

import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ┌─────────────────────────────────────────────────────────┐
 * │  Controller 계층                                         │
 * │  - 클라이언트의 HTTP 요청을 가장 먼저 받는 창구입니다.     │
 * │  - 요청을 Service에 전달하고, 결과를 HTTP 응답으로 반환.  │
 * │  - "어떤 URL로 오면 어떤 메서드를 실행할지" 를 정의합니다. │
 * └─────────────────────────────────────────────────────────┘
 *
 * @RestController  = @Controller + @ResponseBody
 *   → 모든 메서드의 반환값을 자동으로 JSON으로 변환해 응답
 *
 * @RequestMapping("/boards")
 *   → 이 Controller의 모든 URL은 /boards 로 시작
 */
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    // 생성자 주입으로 Service 연결
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // ─────────────────────────────────────────
    // [1] 게시글 생성  POST /boards
    // ─────────────────────────────────────────

    @GetMapping("/Hello")
    public String hello() {
        return "HEllO";
    }

    /**
     * @PostMapping  : HTTP POST 요청을 이 메서드에 연결
     * @RequestBody  : HTTP 요청의 Body(JSON)를 Java 객체로 자동 변환
     *
     * 요청 예시:
     *   POST /boards
     *   Body: { "title": "제목", "content": "내용" }
     *
     * 응답 예시:
     *   200 OK
     *   { "id": 1, "title": "제목", "content": "내용", "createdAt": "..." }
     */
    @PostMapping
    public BoardResponseDto create(@RequestBody BoardRequestDto requestDto) {
        BoardResponseDto response = boardService.create(requestDto);
        return response;
    }

    // ─────────────────────────────────────────
    // [2] 게시글 전체 조회  GET /boards
    // ─────────────────────────────────────────

    /**
     * 요청 예시:  GET /boards
     * 응답 예시:  [ { "id":1, ... }, { "id":2, ... } ]
     */
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {
        List<BoardResponseDto> response = boardService.findAll();
        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────────
    // [3] 게시글 단일 조회  GET /boards/{id}
    // ─────────────────────────────────────────

    /**
     * @PathVariable : URL 경로의 {id} 값을 메서드 파라미터로 받음
     *
     * 요청 예시:  GET /boards/1
     * 응답 예시:  { "id": 1, "title": "제목", "content": "내용", "createdAt": "..." }
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable Long id) {
        BoardResponseDto response = boardService.findById(id);
        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────────
    // [4] 게시글 수정  PUT /boards/{id}
    // ─────────────────────────────────────────

    /**
     * 요청 예시:
     *   PUT /boards/1
     *   Body: { "title": "수정된 제목", "content": "수정된 내용" }
     *
     * 응답 예시:
     *   200 OK
     *   { "id": 1, "title": "수정된 제목", ... }
     */
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> update(
            @PathVariable Long id,
            @RequestBody BoardRequestDto requestDto) {

        BoardResponseDto response = boardService.update(id, requestDto);
        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────────
    // [5] 게시글 삭제  DELETE /boards/{id}
    // ─────────────────────────────────────────

    /**
     * 요청 예시:  DELETE /boards/1
     * 응답 예시:  200 OK  (body 없음)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }
}