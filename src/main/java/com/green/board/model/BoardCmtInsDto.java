package com.green.board.model;

import lombok.Data;

@Data
public class BoardCmtInsDto {
    private Long iboard;
    private String cmt;
    private String writer;
    private String pw;
}
