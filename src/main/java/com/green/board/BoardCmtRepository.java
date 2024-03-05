package com.green.board;

import com.green.board.entity.BoardCmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardCmtRepository extends JpaRepository<BoardCmt, Long> {
    @Modifying
    @Query("delete from BoardCmt bc where bc.board.iboard = :iboard")
    void deleteByBoard(Long iboard);
}
