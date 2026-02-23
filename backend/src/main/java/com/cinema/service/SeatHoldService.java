package com.cinema.service;

import com.cinema.domain.Enums;
import com.cinema.domain.SeatHold;
import com.cinema.dto.BookingDtos;
import com.cinema.repo.SeatHoldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service @RequiredArgsConstructor
public class SeatHoldService {
  private final SeatHoldRepository seatHoldRepository;
  private final StringRedisTemplate redis;
  private final SimpMessagingTemplate ws;
  private static final long HOLD_SECONDS = 300;

  public SeatHold createHold(String userId, BookingDtos.HoldRequest req) {
    var active = seatHoldRepository.findByShowtimeIdAndStatus(req.showtimeId(), Enums.HoldStatus.ACTIVE);
    boolean conflict = active.stream().flatMap(h -> h.getSeatIds().stream()).anyMatch(req.seatIds()::contains);
    if (conflict) throw new RuntimeException("Seats already held");
    var hold = seatHoldRepository.save(SeatHold.builder().showtimeId(req.showtimeId()).seatIds(req.seatIds()).userId(userId)
      .expiresAt(Instant.now().plusSeconds(HOLD_SECONDS)).status(Enums.HoldStatus.ACTIVE).build());
    redis.opsForValue().set("hold:" + hold.getId(), String.join(",", req.seatIds()), HOLD_SECONDS, TimeUnit.SECONDS);
    ws.convertAndSend("/topic/showtimes/" + req.showtimeId(), "HELD");
    return hold;
  }

  public SeatHold validateOwnership(String holdId, String userId) {
    var hold = seatHoldRepository.findById(holdId).orElseThrow();
    if (!hold.getUserId().equals(userId) || hold.getExpiresAt().isBefore(Instant.now()) || hold.getStatus() != Enums.HoldStatus.ACTIVE)
      throw new RuntimeException("Hold invalid");
    return hold;
  }

  @Scheduled(fixedRate = 60000)
  public void releaseExpired() {
    var expired = seatHoldRepository.findByStatusAndExpiresAtBefore(Enums.HoldStatus.ACTIVE, Instant.now());
    for (SeatHold hold : expired) {
      hold.setStatus(Enums.HoldStatus.EXPIRED);
      seatHoldRepository.save(hold);
      redis.delete("hold:" + hold.getId());
      ws.convertAndSend("/topic/showtimes/" + hold.getShowtimeId(), "RELEASED");
    }
  }
}
