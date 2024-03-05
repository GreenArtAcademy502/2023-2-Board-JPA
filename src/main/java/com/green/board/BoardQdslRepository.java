package com.green.board;

import com.green.board.model.BoardSelVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardQdslRepository {
    List<BoardSelVo> selBoardListQdsl(Pageable pageable);
}
