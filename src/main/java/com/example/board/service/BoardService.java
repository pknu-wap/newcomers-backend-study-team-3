package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ┌─────────────────────────────────────────────────────────┐
 * │  Service 계층                                            │
 * │  - 핵심 비즈니스 로직을 처리하는 곳입니다.                 │
 * │  - Controller와 Repository 사이에서 중간 역할을 합니다.    │
 * │  - "어떤 데이터를 어떻게 처리할지"를 결정합니다.           │
 * └─────────────────────────────────────────────────────────┘
 *
 * @Service : 스프링이 "이 클래스는 서비스야" 라고 인식하게 해주는 어노테이션
 */
@Service
public class BoardService {

    // Repository를 사용하기 위해 주입(연결)
    // final + 생성자 주입 방식 = 스프링에서 가장 권장하는 방법
    private final BoardRepository boardRepository;

    /**
     * 생성자 주입 (Constructor Injection)
     * - 스프링이 BoardRepository 객체를 자동으로 여기에 넣어줍니다.
     * - 이것을 "의존성 주입(DI, Dependency Injection)"이라고 합니다.
     */
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // ─────────────────────────────────────────
    // 게시글 생성
    // ─────────────────────────────────────────

    /**
     * 새 게시글을 생성합니다.
     * @param requestDto 제목, 내용이 담긴 요청 데이터
     * @return 생성된 게시글 응답 데이터
     */
    public BoardResponseDto create(BoardRequestDto requestDto) {
        // 1. DTO → Entity 변환
        Board board = new Board(
                null,                        // ID는 Repository에서 자동 부여
                requestDto.getTitle(),
                requestDto.getContent(),
                LocalDateTime.now()          // 현재 시각을 생성 시각으로
        );

        // 2. Repository에 저장
        Board savedBoard = boardRepository.save(board);

        // 3. Entity → DTO 변환 후 반환
        return BoardResponseDto.from(savedBoard);
    }

    // ─────────────────────────────────────────
    // 전체 게시글 조회
    // ─────────────────────────────────────────

    /**
     * 모든 게시글 목록을 반환합니다.
     * @return 게시글 응답 데이터 리스트
     */
    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll()
                .stream()                          // 리스트를 스트림으로 변환
                .map(BoardResponseDto::from)       // 각 Board → BoardResponseDto 변환
                .collect(Collectors.toList());     // 다시 리스트로 수집
    }

    // ─────────────────────────────────────────
    // 단일 게시글 조회
    // ─────────────────────────────────────────

    /**
     * ID로 게시글 하나를 조회합니다.
     * @param id 조회할 게시글 ID
     * @return 게시글 응답 데이터
     * @throws IllegalArgumentException 해당 ID의 게시글이 없는 경우
     */
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id)
                );

        return BoardResponseDto.from(board);
    }

    // ─────────────────────────────────────────
    // 게시글 수정
    // ─────────────────────────────────────────

    /**
     * 게시글을 수정합니다.
     * @param id         수정할 게시글 ID
     * @param requestDto 새로운 제목, 내용
     * @return 수정된 게시글 응답 데이터
     * @throws IllegalArgumentException 해당 ID의 게시글이 없는 경우
     */
    public BoardResponseDto update(Long id, BoardRequestDto requestDto) {
        Board updatedBoard = boardRepository.update(id, requestDto.getTitle(), requestDto.getContent())
                .orElseThrow(() ->
                        new IllegalArgumentException("수정할 게시글을 찾을 수 없습니다. ID: " + id)
                );

        return BoardResponseDto.from(updatedBoard);
    }

    // ─────────────────────────────────────────
    // 게시글 삭제
    // ─────────────────────────────────────────

    /**
     * 게시글을 삭제합니다.
     * @param id 삭제할 게시글 ID
     * @throws IllegalArgumentException 해당 ID의 게시글이 없는 경우
     */
    public void delete(Long id) {
        boolean deleted = boardRepository.deleteById(id);
        if (!deleted) {
            throw new IllegalArgumentException("삭제할 게시글을 찾을 수 없습니다. ID: " + id);
        }
    }
}