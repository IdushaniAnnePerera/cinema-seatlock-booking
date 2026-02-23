package com.cinema.controller;

import com.cinema.dto.AuthDtos;
import com.cinema.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  @PostMapping("/register") public AuthDtos.TokenResponse register(@RequestBody @Valid AuthDtos.RegisterRequest request){ return authService.register(request); }
  @PostMapping("/login") public AuthDtos.TokenResponse login(@RequestBody @Valid AuthDtos.LoginRequest request){ return authService.login(request); }
  @PostMapping("/refresh") public AuthDtos.TokenResponse refresh(@RequestBody Map<String,String> payload){ return authService.refresh(payload.get("refreshToken")); }
}
