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
    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BoardCmt> boardCmts = new ArrayList();

    /*
    orphanRemoval = true은 단독으로 작동하지 않음.
    참고: https://velog.io/@byeongju/orphanRemoval%EC%9D%B4-%EB%8F%99%EC%9E%91%ED%95%98%EC%A7%80-%EC%95%8A%EB%8A%94%EB%8B%A4%EB%A9%B4
         https://github.com/jyami-kim/Jyami-Java-Lab/issues/1

    JPA 표준에 따르면, CASCADE 속성과 orphanRemoval은 독립적이지만 Hibernate 구현체에서는 CASCADE의 영향을 받는 것으로 보입니다.

    case. 부모가 삭제되면
    cascade = CascadeType.REMOVE > 부모, 자식 모두 삭제
    cascade = CascadeType.PERSIST, orphanRemoval = true > 부모, 자식 모두 삭제
    cascade = CascadeType.PERSIST, orphanRemoval = false > Error: 1451-23000: Cannot delete or update a parent row: a foreign key constraint fails 에러 발생
    orphanRemoval = true > 부모, 자식 모두 삭제

    case. 자식이 삭제되면
    cascade = CascadeType.REMOVE > 변화 없음
    cascade = CascadeType.ALL, orphanRemoval = true > 자식 삭제
     */
}
