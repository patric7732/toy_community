package org.example.toy_restboard.domain.board.repository;

import org.example.toy_restboard.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
