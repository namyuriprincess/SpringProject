package com.sparta.springproject;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Comment
    COMMENT_NOT_FOUND("COMMENT-001", "댓글이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("COMMENT-002", "작성자만 삭제/수정할 수 있습니다.", HttpStatus.FORBIDDEN),
    INVALID_PARENT_COMMENT("COMMENT-003", "부모 댓글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PARENT_COMMENT_SCHEDULE_MISMATCH("COMMENT-004", "부모 댓글과 다른 일정에는 대댓글을 달 수 없습니다.", HttpStatus.BAD_REQUEST),
    TOO_DEEP_COMMENT("COMMENT-005", "대댓글은 하나만 작성 가능합니다.", HttpStatus.BAD_REQUEST),


    // Schedule
    SCHEDULE_NOT_FOUND("SCHEDULE-001", "해당 일정이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    INVALID_SCHEDULE_ACCESS("SCHEDULE-002", "일정을 수정/삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // User
    EMAIL_ALREADY_EXISTS("USER-001", "이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("USER-002", "존재하지 않는 이메일입니다.", HttpStatus.NOT_FOUND),
    PASSWORD_MISMATCH("USER-003", "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    USER_ID_NOT_FOUND("USER-004", "해당 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus; // ← 이거 초기화!
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
