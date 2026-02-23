package com.cinema.auth;

import com.cinema.domain.Enums;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
  private final SecretKey key;
  @Value("${security.jwt.access-exp-min}") private long accessExpMin;
  @Value("${security.jwt.refresh-exp-days}") private long refreshExpDays;

  public JwtService(@Value("${security.jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String accessToken(String userId, Enums.Role role) {
    return Jwts.builder().subject(userId).claims(Map.of("role", role.name(), "type", "access"))
      .issuedAt(new Date()).expiration(Date.from(Instant.now().plusSeconds(accessExpMin * 60))).signWith(key).compact();
  }

  public String refreshToken(String userId) {
    return Jwts.builder().subject(userId).claims(Map.of("type", "refresh"))
      .issuedAt(new Date()).expiration(Date.from(Instant.now().plusSeconds(refreshExpDays * 86400))).signWith(key).compact();
  }

  public Claims parse(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
  }
}
