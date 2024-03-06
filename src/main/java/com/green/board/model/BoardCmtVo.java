package com.green.board.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardCmtVo {
    private Long icmt;
    private String cmt;
    private String writer;
    private LocalDateTime createdAt;
}
