package com.example.board.service;

import com.example.board.model.entity.Board;
import com.example.board.model.entity.BoardStatus;
import com.example.board.model.response.BoardResponse;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.BoardRepositoryCustom;
import com.example.board.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    private final BoardRepositoryCustom boardRepositoryCustom;

    public List<BoardResponse> searchBoardList(int page, int pageSize, Sort.Direction direction) {
        return boardRepository.findAll(PageRequest.of(page,pageSize,Sort.by(direction,"boardNo"))
        ).map(BoardResponse::from).toList();
    }

    public BoardResponse getBoard(Long boardNo) {
        return boardRepository.findById(boardNo)
                .map(BoardResponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    public BoardResponse writeBoard(String title, String body) {
        Board board = new Board();
        board.setTitle(title);
        board.setBody(body);
        board.setBoardStatus(BoardStatus.ACTIVE);
        return BoardResponse.from(boardRepository.save(board));
    }


    public BoardResponse editBoard(Long boardNo, String body) {
        return boardRepository.findById(boardNo)
                .map(board -> {
                    board.setBody(body);
                    return board;
                }).map(BoardResponse::from)
                .orElseThrow(()->new RuntimeException("존재하지 않는 게시글입니다."));
    }

    public Long deleteBoard(Long boardNo) {
        return boardRepositoryCustom.find(boardNo)
                .map(board -> {
                    commentRepository.deleteAll(board.getComments());
                    boardRepository.delete(board);
                    return board.getBoardNo();
                }).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }
}
