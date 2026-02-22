package com.cinema.controller;

import com.cinema.domain.*;
import com.cinema.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/admin") @RequiredArgsConstructor @PreAuthorize("hasRole('ADMIN')")
public class AdminController {
  private final MovieRepository movies; private final CinemaRepository cinemas; private final ScreenRepository screens; private final SeatLayoutRepository layouts; private final ShowtimeRepository showtimes; private final PromoRepository promos; private final BookingRepository bookings; private final AuditEventRepository audits;
  @PostMapping("/movies") public Movie saveMovie(@RequestBody Movie m){ return movies.save(m); }
  @PostMapping("/cinemas") public Cinema saveCinema(@RequestBody Cinema c){ return cinemas.save(c); }
  @PostMapping("/screens") public Screen saveScreen(@RequestBody Screen s){ return screens.save(s); }
  @PostMapping("/layouts") public SeatLayout saveLayout(@RequestBody SeatLayout l){ return layouts.save(l); }
  @PostMapping("/showtimes") public Showtime saveShowtime(@RequestBody Showtime s){ return showtimes.save(s); }
  @PostMapping("/promos") public Promo savePromo(@RequestBody Promo p){ return promos.save(p); }
  @GetMapping("/analytics") public Map<String,Object> analytics(){ return Map.of("bookings", bookings.count(), "audits", audits.count()); }
}
