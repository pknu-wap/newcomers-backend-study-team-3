package com.example.demo.service;

import com.example.demo.dto.CreateArticleRequest;
import com.example.demo.entity.Article;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Article CreateArticle(CreateArticleRequest request) {
        Article article = Article.from(request);
        boardRepository.save(article);
        return article;
    }
}
