package com.green.board;

import com.green.board.entity.Board;
import com.green.board.model.BoardSelVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardQdslRepository {
    List<Board> findAllByOrderByIboardDesc(Pageable pageable);

    @Query("select b from Board b order by b.iboard desc")
    List<Board> selBoardList(Pageable pageable);

    @Query("select new com.green.board.model.BoardSelVo(b.iboard, b.title, b.writer, b.createdAt) " +
            "from Board b order by b.iboard desc")
    List<BoardSelVo> selBoardList2(Pageable pageable);
}
