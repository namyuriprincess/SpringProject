Spring Boot 댓글 기능 기반 REST API

📝 프로젝트 개요
댓글(Comment) 기능을 중심으로 사용자 인증(JWT), 일정(Schedule)과 대댓글 구조를 포함한 CRUD API를 구현한 Spring Boot 프로젝트이다.

📮 API 명세 요약

👤 사용자 API

- POST /api/user/signup – 회원가입 (요청: email, password, nickname, userRole)

- POST /api/user/login – 로그인 및 JWT 발급 (요청: email, password)

- GET /api/user/{id} – 사용자 정보 조회 (인증 필요)

📆 일정 API

- POST /api/schedule – 일정 생성 (요청: title, content, 인증 필요)

- GET /api/schedule/{id} – 일정 단건 조회 (인증 필요)

- GET /api/schedule – 전체 일정 목록 조회 (인증 필요)

- PUT /api/schedule/{id} – 일정 수정 (요청: title, content, 인증 필요)

- DELETE /api/schedule/{id} – 일정 삭제 (인증 필요)

💬 댓글 API

- POST /api/schedule/{scheduleId}/comment
→ 댓글 등록 (요청: content, parentId는 선택 / 인증 필요)

- GET /api/schedule/{scheduleId}/comment
→ 댓글 및 대댓글 목록 조회 (인증 필요)

- PUT /api/comment/{commentId}
→ 댓글 수정 (요청: content / 인증 필요)

- DELETE /api/comment/{commentId}
→ 댓글 삭제 (인증 필요)

⚠️ 예외 처리

- GlobalExceptionHandler에서 통합 처리

- ErrorCode enum 기반으로 도메인별 에러코드 관리

- 일관된 에러 응답 형식 사용:
- {
  "code": "COMMENT-001",
  "message": "댓글이 존재하지 않습니다.",
  "status": 404
  }
기능 테스트 사진
![img.png](img.png)
![img_3.png](img_3.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_4.png](img_4.png)
![img_5.png](img_5.png)
![img_6.png](img_6.png)
![img_7.png](img_7.png)
![img_8.png](img_8.png)
![img_9.png](img_9.png)
![img_11.png](img_11.png)
![img_10.png](img_10.png)
![img_12.png](img_12.png)
![img_13.png](img_13.png)
![img_14.png](img_14.png)
![img_15.png](img_15.png)
![img_16.png](img_16.png)
![img_17.png](img_17.png)
![img_18.png](img_18.png)