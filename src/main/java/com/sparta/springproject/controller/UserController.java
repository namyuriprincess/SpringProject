package com.sparta.springproject.controller;

import com.sparta.springproject.dto.SigninResponse;
import com.sparta.springproject.dto.UserRequestDto;
import com.sparta.springproject.dto.UserResponseDto;
import com.sparta.springproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<SigninResponse> login(@RequestBody UserRequestDto requestDto) {
        String token = userService.login(requestDto);
        return ResponseEntity.ok(new SigninResponse(token));
    }

    // 사용자 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
