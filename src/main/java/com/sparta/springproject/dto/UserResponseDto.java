package com.sparta.springproject.dto;

import com.sparta.springproject.UserRole;
import com.sparta.springproject.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private UserRole userRole;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.userRole = user.getUserRole();
    }
}
