package com.green.board;

import com.green.board.model.BoardSelVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.green.board.entity.QBoard.board;

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
}
