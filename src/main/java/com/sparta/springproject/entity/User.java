package com.sparta.springproject.entity;

import com.sparta.springproject.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserRole userRole;

  @Column(nullable = false)
  private String nickname;

  public User(String email, String password, UserRole userRole, String nickname) {
    this.email = email;
    this.password = password;
    this.userRole = userRole;
    this.nickname = nickname;
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public void updateRole(UserRole userRole) {
    this.userRole = userRole;
  }
}

