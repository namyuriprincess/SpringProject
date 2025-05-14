package com.sparta.springproject;

import com.sparta.springproject.dto.UserRequestDto;
import com.sparta.springproject.dto.UserResponseDto;
import com.sparta.springproject.repository.UserRepository;
import com.sparta.springproject.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 성공")
    void signup_success() {
        // given
        UserRequestDto request = new UserRequestDto();
        request.setEmail("test@example.com");
        request.setPassword("securePassword123!");
        request.setNickname("tester");
        request.setUserRole(UserRole.USER);

        // when
        UserResponseDto response = userService.signup(request);

        // then
        assertThat(response.getEmail()).isEqualTo("test@example.com");
        assertThat(userRepository.findByEmail("test@example.com")).isPresent();
    }

    @Test
    @DisplayName("회원가입 실패 - 중복 이메일")
    void signup_duplicateEmail_fail() {
        // given
        String email = "dup@example.com";
        UserRequestDto request = new UserRequestDto();
        request.setEmail(email);
        request.setPassword("pass");
        request.setNickname("nickname");
        request.setUserRole(UserRole.USER);

        userService.signup(request); // 이미 한 번 가입

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.signup(request);
        });
    }

    @Test
    @DisplayName("로그인 성공")
    void login_success() {
        // given
        String email = "login@example.com";
        String password = "mypassword";

        UserRequestDto signupRequest = new UserRequestDto();
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);
        signupRequest.setNickname("loginUser");
        signupRequest.setUserRole(UserRole.USER);
        userService.signup(signupRequest);

        UserRequestDto loginRequest = new UserRequestDto();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        // when
        String result = userService.login(loginRequest);

        // then
        assertThat(result).isEqualTo("로그인 성공");
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void login_wrongPassword_fail() {
        // given
        String email = "wrongpw@example.com";

        UserRequestDto signupRequest = new UserRequestDto();
        signupRequest.setEmail(email);
        signupRequest.setPassword("correctPassword");
        signupRequest.setNickname("wrongPW");
        signupRequest.setUserRole(UserRole.USER);
        userService.signup(signupRequest);

        UserRequestDto loginRequest = new UserRequestDto();
        loginRequest.setEmail(email);
        loginRequest.setPassword("wrongPassword");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login(loginRequest);
        });
    }
}
