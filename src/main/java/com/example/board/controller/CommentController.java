package com.example.board.controller;

import com.example.board.model.request.CommentDeleteRequest;
import com.example.board.model.request.CommentEditRequest;
import com.example.board.model.request.CommentPostRequest;
import com.example.board.model.response.BoardResponse;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("comment")
    public BoardResponse post(@RequestBody CommentPostRequest commentPostRequest) {
        return commentService.postComment(commentPostRequest.getBoardNo(), commentPostRequest.getCommentBody());
    }

    @PutMapping("comment")
    public String edit(@RequestBody CommentEditRequest commentEditRequest) {
        commentService.editComment(commentEditRequest.getBoardNo(), commentEditRequest.getCommentNo(), commentEditRequest.getCommentBody());
        return "ok";
    }

    @DeleteMapping("comment")
    public String delete(@RequestBody CommentDeleteRequest commentDeleteRequest) {

        commentService.deleteComment(commentDeleteRequest.getBoardNo(), commentDeleteRequest.getCommentNo());

        return "ok";
    }
}
