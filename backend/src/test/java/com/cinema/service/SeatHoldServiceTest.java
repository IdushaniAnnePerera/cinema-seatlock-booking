package com.cinema.service;

import com.cinema.domain.Enums;
import com.cinema.domain.SeatHold;
import com.cinema.dto.BookingDtos;
import com.cinema.repo.SeatHoldRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeatHoldServiceTest {
  @Mock SeatHoldRepository repo; @Mock StringRedisTemplate redis; @Mock SimpMessagingTemplate ws; @Mock ValueOperations<String,String> valueOps;
  @InjectMocks SeatHoldService service;

  @Test void createsHoldWhenNoConflict() {
    when(repo.findByShowtimeIdAndStatus("s1", Enums.HoldStatus.ACTIVE)).thenReturn(List.of());
    when(repo.save(any())).thenAnswer(i -> { SeatHold h=i.getArgument(0); h.setId("h1"); return h;});
    when(redis.opsForValue()).thenReturn(valueOps);
    var hold = service.createHold("u1", new BookingDtos.HoldRequest("s1", List.of("A1")));
    assertEquals("h1", hold.getId());
  }

  @Test void rejectsExpiredOwnership() {
    when(repo.findById("h1")).thenReturn(java.util.Optional.of(SeatHold.builder().id("h1").userId("u1").expiresAt(Instant.now().minusSeconds(1)).status(Enums.HoldStatus.ACTIVE).build()));
    assertThrows(RuntimeException.class, () -> service.validateOwnership("h1", "u1"));
  }
}
