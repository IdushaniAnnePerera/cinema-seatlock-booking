package com.cinema.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDtos {
  public record RegisterRequest(@Email String email, @NotBlank String password, @NotBlank String fullName) {}
  public record LoginRequest(@Email String email, @NotBlank String password) {}
  public record TokenResponse(String accessToken, String refreshToken, String role, String userId, String fullName) {}
}
