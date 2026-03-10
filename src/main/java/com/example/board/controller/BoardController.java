package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 생성 API: POST http://localhost:8080/boards
    @PostMapping
    public String create(@RequestBody Map<String, String> body) {
        boardService.create(body.get("title"), body.get("content"));
        return "OK";
    }

    // 조회 API: GET http://localhost:8080/boards
    @GetMapping
    public List<Board> list() {
        return boardService.findAll();
    }

    // 단일 조회 API: GET http://localhost:8080/boards/1 (1번 게시글 조회)
    @GetMapping("/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardService.findById(id);
    }
}