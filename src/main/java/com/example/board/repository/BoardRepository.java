package com.example.board.repository;

import com.example.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<다룰 데이터 클래스, ID의 타입>
public interface BoardRepository extends JpaRepository<Board, Long> {
}