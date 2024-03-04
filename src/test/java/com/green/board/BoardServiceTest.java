package com.green.board;

import com.green.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class BoardServiceTest {
    @InjectMocks
    private BoardService service;

    @Mock
    private BoardRepository repository;

    @Test
    public void insBoard() {

        Board board = new Board();
        board.setIboard(1L);
        given(repository.save(any())).willReturn(board);

        Long result = service.postBoard(board);

        verify(repository).save(any());
        assertEquals(1L, result);

    }
}