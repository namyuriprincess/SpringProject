package com.sparta.springproject.service;

import com.sparta.springproject.CustomException;
import com.sparta.springproject.ErrorCode;
import com.sparta.springproject.JwtUtil;
import com.sparta.springproject.WebSecurityConfig;
import com.sparta.springproject.dto.UserRequestDto;
import com.sparta.springproject.dto.UserResponseDto;
import com.sparta.springproject.entity.User;
import com.sparta.springproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    // 회원가입
    public UserResponseDto signup(UserRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(
                requestDto.getEmail(),
                encodedPassword,
                requestDto.getUserRole(),
                requestDto.getNickname()
        );

        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    // 로그인
    public String login(UserRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        // 실제 JWT 토큰 생성
        return jwtUtil.createToken(user.getEmail(), user.getUserRole());
    }

    // 사용자 조회
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_ID_NOT_FOUND));
        return new UserResponseDto(user);
    }
}
