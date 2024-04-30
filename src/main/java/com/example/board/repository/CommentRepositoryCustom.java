package com.example.board.repository;

import com.example.board.model.entity.Comment;
import com.example.board.model.entity.QBoard;
import com.example.board.model.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private static final QComment comment = QComment.comment;

    public Comment find(Long commentNo, Long boardNo) {
        return queryFactory.select(comment)
                .from(comment)
                .where(comment.commentNo.eq(commentNo))
                .where(QBoard.board.boardNo.eq(boardNo))
                .fetchOne();
    }
}
