package com.sparta.springproject.dto;

import com.sparta.springproject.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;
    private String nickname;
    private UserRole userRole;
}
