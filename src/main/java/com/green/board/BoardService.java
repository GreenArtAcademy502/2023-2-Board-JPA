package com.green.board;

import com.green.board.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;

    public Long postBoard(Board board) {
        String hashedPw = BCrypt.hashpw(board.getPw(), BCrypt.gensalt());
        board.setPw(hashedPw);
        repository.save(board);
        return board.getIboard();
    }
}
