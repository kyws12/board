package com.example.board.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDeleteRequest {

    private Long boardNo;
    private Long commentNo;
}
