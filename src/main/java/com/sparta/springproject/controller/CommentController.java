package com.sparta.springproject.controller;

import com.sparta.springproject.dto.comment.CommentRequestDto;
import com.sparta.springproject.dto.comment.CommentResponseDto;
import com.sparta.springproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글/대댓글 등록
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.createComment(requestDto));
    }

    // 일정별 댓글 전체 조회
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsBySchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getCommentsBySchedule(scheduleId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody String newContent
    ) {
        return ResponseEntity.ok(commentService.updateComment(id, newContent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
