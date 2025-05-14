package com.sparta.springproject.dto.comment;

import com.sparta.springproject.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long writerId;
    private Long scheduleId;
    private Long parentCommentId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writerId = comment.getWriterId();
        this.scheduleId = comment.getScheduleId();
        this.parentCommentId = comment.getParentCommentId();
    }
}
