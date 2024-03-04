package com.green.board;

import com.green.board.entity.Board;
import com.green.board.entity.BoardCmt;
import com.green.board.model.BoardCmtInsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

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

    public void delBoard(Long iboard) {
        Board board = repository.getReferenceById(iboard);
        repository.delete(board);
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
