package com.sparta.springproject;

import com.sparta.springproject.dto.comment.CommentRequestDto;
import com.sparta.springproject.dto.comment.CommentResponseDto;
import com.sparta.springproject.repository.CommentRepository;
import com.sparta.springproject.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("댓글 등록 테스트")
    void createComment_success() {
        // given
        CommentRequestDto request = new CommentRequestDto();
        request.setContent("테스트 댓글입니다");
        request.setWriterId(1L);
        request.setScheduleId(1L);
        request.setParentCommentId(null); // 일반 댓글

        // when
        CommentResponseDto response = commentService.createComment(request);

        // then
        assertThat(response.getContent()).isEqualTo("테스트 댓글입니다");
        assertThat(response.getScheduleId()).isEqualTo(1L);
        assertThat(response.getParentCommentId()).isNull();
    }

    @Test
    @DisplayName("댓글 목록 조회 테스트")
    void getCommentsBySchedule_success() {
        // given
        Long scheduleId = 1L;

        // when
        List<CommentResponseDto> comments = commentService.getCommentsBySchedule(scheduleId);

        // then
        assertThat(comments).isNotNull();
        assertThat(comments).allMatch(comment -> comment.getScheduleId().equals(scheduleId));
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment_success() {
        // given
        CommentRequestDto request = new CommentRequestDto();
        request.setContent("수정 전 댓글");
        request.setWriterId(1L);
        request.setScheduleId(1L);
        CommentResponseDto saved = commentService.createComment(request);

        // when
        String newContent = "수정된 댓글";
        CommentResponseDto updated = commentService.updateComment(saved.getId(), newContent);

        // then
        assertThat(updated.getContent()).isEqualTo("수정된 댓글");
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteComment_success() {
        // given
        CommentRequestDto request = new CommentRequestDto();
        request.setContent("삭제할 댓글");
        request.setWriterId(1L);
        request.setScheduleId(1L);
        CommentResponseDto saved = commentService.createComment(request);

        // when
        commentService.deleteComment(saved.getId());

        // then
        assertThat(commentRepository.findById(saved.getId())).isEmpty();
    }

}
