package com.sparta.springproject.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class CommentRequestDto {
    private String content;
    private Long writerId;
    private Long scheduleId;
    private Long parentCommentId; // 대댓글이 아니면 null\


}
