package com.green.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardSelVo {
    private Long iboard;
    private String title;
    private String writer;
    private LocalDateTime createdAt;
}
