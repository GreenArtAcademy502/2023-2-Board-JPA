package com.green.board;

import com.green.board.entity.Board;
import com.green.board.entity.BoardCmt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCmtRepository extends JpaRepository<BoardCmt, Long> {
    void deleteByBoard(Board board);
}
