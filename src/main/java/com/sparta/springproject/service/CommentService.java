package com.sparta.springproject.service;

import com.sparta.springproject.CustomException;
import com.sparta.springproject.ErrorCode;
import com.sparta.springproject.JwtUtil;
import com.sparta.springproject.dto.comment.CommentResponseDto;
import com.sparta.springproject.dto.comment.CommentRequestDto;
import com.sparta.springproject.entity.Schedule;
import com.sparta.springproject.entity.User;
import com.sparta.springproject.repository.CommentRepository;
import com.sparta.springproject.repository.ScheduleRepository;
import com.sparta.springproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.sparta.springproject.entity.Comment;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final JwtUtil jwtUtil;

    // 댓글/대댓글 등록
    public CommentResponseDto createComment(CommentRequestDto requestDto ,Long writerId){



        Comment parent = null;

        // 대댓글 제한
        if (requestDto.getParentCommentId() != null) {
            parent = commentRepository.findById(requestDto.getParentCommentId())
                    .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

            System.out.println("parentCommentId: " + requestDto.getParentCommentId());
            System.out.println("찾은 parent: " + parent);
            System.out.println("부모의 부모: " + parent.getParentCommentId());

            if (parent.getParentCommentId() != null) {
                throw new CustomException(ErrorCode.TOO_DEEP_COMMENT);
            }
        }


        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        User writer = userRepository.findById(writerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Comment comment = new Comment(
                requestDto.getContent(),
                writerId,
                requestDto.getScheduleId(),
                requestDto.getParentCommentId()
        );

        commentRepository.save(comment);
        return new CommentResponseDto(commentRepository.save(comment));
    }

    // 댓글 목록 조회
    public List<CommentResponseDto> getCommentsBySchedule(Long scheduleId) {
        return commentRepository.findAllByScheduleIdOrderByCreatedAtAsc(scheduleId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
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
