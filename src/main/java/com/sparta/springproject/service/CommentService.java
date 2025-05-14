package com.sparta.springproject.service;

import com.sparta.springproject.dto.comment.CommentResponseDto;
import com.sparta.springproject.dto.comment.CommentRequestDto;
import com.sparta.springproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.sparta.springproject.entity.Comment;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 댓글/대댓글 등록
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Comment comment = new Comment(
                requestDto.getContent(),
                requestDto.getWriterId(),
                requestDto.getScheduleId(),
                requestDto.getParentCommentId()
        );
        return new CommentResponseDto(commentRepository.save(comment));
    }

    // 댓글 목록 조회
    public List<CommentResponseDto> getCommentsBySchedule(Long scheduleId) {
        return commentRepository.findAllByScheduleIdOrderByCreatedAtAsc(scheduleId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }


    public CommentResponseDto updateComment(Long id, String newContent) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        comment.updateContent(newContent);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);
    }
}
