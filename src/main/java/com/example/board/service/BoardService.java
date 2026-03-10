package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 저장 로직
    public void create(String title, String content) {
        Board board = new Board(title, content);
        boardRepository.save(board);
    }

    // 게시글 전체 조회 로직
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 특정 ID의 게시글 하나만 조회하는 로직
    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }
}
