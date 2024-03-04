package com.green.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Board extends BaseEntity {
    @JsonIgnore
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iboard;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ctnt;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String pw;

    //양방향, cmt의 정보를 알 수 있게끔.
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "board")
    private List<BoardCmt> boardCmts = new ArrayList();

}
