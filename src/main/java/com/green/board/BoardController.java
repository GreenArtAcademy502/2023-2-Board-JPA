package com.green.board;

import com.green.board.entity.Board;
import com.green.board.entity.BoardCmt;
import com.green.board.model.BoardCmtInsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    @PostMapping
    public Long postBoard(@RequestBody Board board) {
        return service.postBoard(board);
    }

    @DeleteMapping
    public Long deleteBoard(@RequestParam Long iboard) {
        service.delBoard(iboard);
        return 1L;
    }

    //빨간줄 제거
    //BoardCmt에 JsonIgnore 애노테이션

    @PostMapping("/cmt")
    public Long postBoardCmt(@RequestBody BoardCmtInsDto dto) {
        return service.postBoardCmt(dto);
    }

}
