package com.green.board;

import com.green.board.model.BoardCmtVo;
import com.green.board.model.BoardDetailVo;
import com.green.board.model.BoardSelVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.green.board.entity.QBoard.board;
import static com.green.board.entity.QBoardCmt.boardCmt;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;


@Slf4j
@RequiredArgsConstructor
public class BoardQdslRepositoryImpl implements BoardQdslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardSelVo> selBoardListQdsl(Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(BoardSelVo.class
                        , board.iboard, board.title, board.writer, board.createdAt))
                .from(board)
                .orderBy(board.iboard.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public BoardDetailVo selBoardQdsl(Long iboard) {
        List<BoardDetailVo> list = jpaQueryFactory.from(board)
                .leftJoin(board.boardCmts, boardCmt)
                .where(board.iboard.eq(iboard))
                .transform(
                        groupBy(board.iboard).list(Projections.constructor(BoardDetailVo.class,board.iboard, board.title, board.ctnt, board.writer, board.createdAt
                                , list(Projections.constructor(BoardCmtVo.class, boardCmt.icmt, boardCmt.cmt, boardCmt.writer, boardCmt.createdAt))))
                );

                return list.size() > 0 ? list.get(0) : null;
    }
}
