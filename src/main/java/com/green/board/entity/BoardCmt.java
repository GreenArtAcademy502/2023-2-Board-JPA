package com.green.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BoardCmt extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long icmt;

    @ManyToOne
    @JoinColumn(name = "iboard", nullable = false)
    private Board board;

    @Column(length = 300, nullable = false)
    private String cmt;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String pw;
}
