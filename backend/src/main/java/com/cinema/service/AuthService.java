package com.cinema.service;

import com.cinema.auth.JwtService;
import com.cinema.domain.Enums;
import com.cinema.domain.User;
import com.cinema.dto.AuthDtos;
import com.cinema.repo.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthDtos.TokenResponse register(AuthDtos.RegisterRequest req) {
    User user = userRepository.save(User.builder()
      .email(req.email())
      .fullName(req.fullName())
      .passwordHash(passwordEncoder.encode(req.password()))
      .role(Enums.Role.CUSTOMER)
      .build());
    return tokens(user);
  }

  public AuthDtos.TokenResponse login(AuthDtos.LoginRequest req) {
    User user = userRepository.findByEmail(req.email()).orElseThrow();
    if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) throw new RuntimeException("Invalid credentials");
    return tokens(user);
  }

  public AuthDtos.TokenResponse refresh(String refreshToken) {
    Claims claims = jwtService.parse(refreshToken);
    if (!"refresh".equals(claims.get("type", String.class))) throw new RuntimeException("Invalid token");
    String userId = claims.getSubject();
    User user = userRepository.findById(userId).orElseThrow();
    return tokens(user);
  }

  private AuthDtos.TokenResponse tokens(User user) {
    return new AuthDtos.TokenResponse(
      jwtService.accessToken(user.getId(), user.getRole()),
      jwtService.refreshToken(user.getId()),
      user.getRole().name(),
      user.getId(),
      user.getFullName()
    );
  }
}
