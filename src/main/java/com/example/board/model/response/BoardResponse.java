package com.example.board.model.response;

import com.example.board.model.entity.Board;
import com.example.board.model.entity.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class BoardResponse {

    private Long boardNo;
    private String title;
    private String body;
    private BoardStatus boardStatus;
    private List<CommentResponse> comments;

    static public BoardResponse from(Board board) {
        return new BoardResponse(
                board.getBoardNo(),
                board.getTitle(),
                board.getBody(),
                board.getBoardStatus(),
                board.getComments().stream().map(CommentResponse::from).collect(Collectors.toList()));
    }
}
