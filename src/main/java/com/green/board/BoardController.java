package com.green.board;

import com.green.board.entity.Board;
import com.green.board.entity.BoardCmt;
import com.green.board.model.BoardCmtInsDto;
import com.green.board.model.BoardSelVo;
import com.green.board.model.BoardUpdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping
    public List<BoardSelVo> getBoardList(Pageable pageable) {
        return service.getBoardList(pageable);
    }

    @PutMapping
    public Long putBoard(@RequestBody BoardUpdDto dto) {
        return service.putBoard(dto);
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
