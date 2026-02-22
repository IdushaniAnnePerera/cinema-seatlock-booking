package com.cinema.controller;

import com.cinema.dto.BookingDtos;
import com.cinema.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api") @RequiredArgsConstructor
public class BookingController {
  private final BookingService bookingService;

  @PreAuthorize("hasRole('CUSTOMER')")
  @PostMapping("/bookings/hold")
  public BookingDtos.HoldResponse hold(Authentication auth, @RequestBody @Valid BookingDtos.HoldRequest req){ return bookingService.hold(auth.getName(), req); }

  @PreAuthorize("hasRole('CUSTOMER')")
  @PostMapping("/bookings/confirm")
  public BookingDtos.BookingResponse confirm(Authentication auth, @RequestBody @Valid BookingDtos.ConfirmRequest req){ return bookingService.confirm(auth.getName(), req); }

  @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
  @PostMapping("/tickets/validate")
  public Map<String,String> validate(@RequestBody Map<String,String> payload){ return Map.of("status", bookingService.checkin(payload.get("token"))); }
}
