package com.example.demo.controller;

import com.example.demo.dto.CreateArticleRequest;
import com.example.demo.entity.Article;
import com.example.demo.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Article CreateArticle(@RequestBody CreateArticleRequest request) {
        return boardService.CreateArticle(request);
    }
}
