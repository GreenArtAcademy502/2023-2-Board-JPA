package com.green.board;

import com.green.board.entity.Board;
import com.green.board.entity.BoardCmt;
import com.green.board.model.BoardCmtInsDto;
import com.green.board.model.BoardSelVo;
import com.green.board.model.BoardUpdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;
    private final BoardCmtRepository cmtRepository;

    public Long postBoard(Board board) {
        String hashedPw = BCrypt.hashpw(board.getPw(), BCrypt.gensalt());
        board.setPw(hashedPw);
        repository.save(board);
        return board.getIboard();
    }

    public List<BoardSelVo> getBoardList(Pageable pageable) {
        Page<Board> list = repository.findAll(pageable); //1 Default Query Method

        List<BoardSelVo> result = new ArrayList();
        for(Board item : list.getContent()) {
            result.add(BoardSelVo.builder()
                                .iboard(item.getIboard())
                                .title(item.getTitle())
                                .writer(item.getWriter())
                                .createdAt(item.getCreatedAt())
                                .build()
            );
        }

        return result;

        //2 Custom Query Method

        //3 JPQL

        //4 QueryDSL
    }

    @Transactional
    public void delBoard(Long iboard) {
        Board board = repository.getReferenceById(iboard);
        cmtRepository.deleteByBoard(iboard);
        repository.delete(board);
    }

    //return 수정 > 1, 실패 > 0
    @Transactional
    public Long putBoard(BoardUpdDto dto) {
        Board board = repository.getReferenceById(dto.getIboard());
        if(!BCrypt.checkpw(dto.getPw(), board.getPw())) {
            return 0L;
        }
        board.setTitle(dto.getTitle());
        board.setCtnt(dto.getCtnt());
        return 1L;
    }

    public Long postBoardCmt(BoardCmtInsDto dto) {
        Board board = new Board();
        board.setIboard(dto.getIboard());

        Board board2 = repository.getReferenceById(dto.getIboard());

        String hashedPw = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());

        BoardCmt boardCmt = new BoardCmt();
        boardCmt.setBoard(board2);
        boardCmt.setCmt(dto.getCmt());
        boardCmt.setWriter(dto.getWriter());
        boardCmt.setPw(hashedPw);
        cmtRepository.save(boardCmt);
        return boardCmt.getIcmt();
    }
}
