package com.example.board.repository;

import com.example.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ┌─────────────────────────────────────────────────────────┐
 * │  Repository 계층                                         │
 * │  - 데이터를 저장하고 꺼내오는 역할을 합니다.               │
 * │  - 실제 프로젝트에서는 DB(MySQL 등)를 사용하지만,          │
 * │    여기서는 HashMap(메모리)에 저장합니다.                  │
 * └─────────────────────────────────────────────────────────┘
 *
 * @Repository : 스프링이 "이 클래스는 저장소야" 라고 인식하게 해주는 어노테이션
 */
@Repository
public class BoardRepository {

    // 데이터를 임시로 저장하는 Map (DB 역할)
    // key = 게시글 ID, value = Board 객체
    private final Map<Long, Board> store = new HashMap<>();

    // 자동으로 1씩 증가하는 ID 카운터
    private Long sequence = 0L;

    // ─────────────────────────────────────────
    // 저장 (Create)
    // ─────────────────────────────────────────

    /**
     * 게시글을 저장합니다.
     * @param board 저장할 게시글 객체
     * @return 저장된 게시글 객체 (ID가 자동 부여됨)
     */
    public Board save(Board board) {
        // ID 자동 부여
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    // ─────────────────────────────────────────
    // 조회 (Read)
    // ─────────────────────────────────────────

    /**
     * 전체 게시글 목록을 반환합니다.
     * @return 게시글 리스트
     */
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * ID로 특정 게시글 하나를 찾습니다.
     * @param id 찾을 게시글 ID
     * @return Optional<Board> - 없을 수도 있어서 Optional로 감쌈
     *
     * Optional이란?
     * - "있을 수도 있고 없을 수도 있는 값"을 안전하게 다루는 래퍼 클래스
     * - null을 직접 다루면 NullPointerException 발생 위험이 있어서 사용
     */
    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // ─────────────────────────────────────────
    // 수정 (Update)
    // ─────────────────────────────────────────

    /**
     * ID에 해당하는 게시글을 수정합니다.
     * @param id      수정할 게시글 ID
     * @param title   새 제목
     * @param content 새 내용
     * @return 수정된 게시글 Optional
     */
    public Optional<Board> update(Long id, String title, String content) {
        return findById(id).map(board -> {
            board.setTitle(title);
            board.setContent(content);
            return board;
        });
    }

    // ─────────────────────────────────────────
    // 삭제 (Delete)
    // ─────────────────────────────────────────

    /**
     * ID에 해당하는 게시글을 삭제합니다.
     * @param id 삭제할 게시글 ID
     * @return 삭제 성공 여부 (true / false)
     */
    public boolean deleteById(Long id) {
        if (store.containsKey(id)) {
            store.remove(id);
            return true;
        }
        return false;
    }
}