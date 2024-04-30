package com.example.board.service;

import com.example.board.model.entity.Comment;
import com.example.board.model.response.BoardResponse;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.BoardRepositoryCustom;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.CommentRepositoryCustom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final BoardRepository boardRepository;

    private final BoardRepositoryCustom boardRepositoryCustom;

    private final CommentRepositoryCustom commentRepositoryCustom;

    private final CommentRepository commentRepository;

    public BoardResponse postComment(Long boardId, String commentBody) {
        return boardRepositoryCustom.find(boardId)
                .map(board -> board.addComment(commentBody))
                .map(boardRepository::save)
                .map(BoardResponse::from)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    public void editComment(Long boardNo, Long commentNo, String commentBody) {
        Comment comment = commentRepositoryCustom.find(commentNo,boardNo);
        if (comment ==null) throw new RuntimeException("존재하지 않는 댓글입니다.");

        comment.setBody(commentBody);

    }

    public void deleteComment(Long boardNo, Long commentNo) {
        Comment comment = commentRepositoryCustom.find(commentNo,boardNo);
        if (comment ==null) throw new RuntimeException("존재하지 않는 댓글입니다.");
        commentRepository.delete(comment);
    }
}
