package com.cinema.service;

import com.cinema.domain.*;
import com.cinema.dto.BookingDtos;
import com.cinema.repo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
  @Mock SeatHoldService holdService; @Mock BookingRepository bookings; @Mock PaymentRepository payments; @Mock TicketRepository tickets; @Mock SeatHoldRepository holds;
  @InjectMocks BookingService service;

  @Test void checkinMarksTicket() {
    var t = Ticket.builder().id("t1").bookingId("b1").qrToken("tok").build();
    when(tickets.findByQrToken("tok")).thenReturn(Optional.of(t));
    var result = service.checkin("tok");
    assertEquals("CHECKED_IN", result);
    verify(tickets).save(argThat(saved -> saved.getCheckedInAt()!=null));
  }

  @Test void confirmConvertsHold() {
    var h = SeatHold.builder().id("h1").showtimeId("s1").seatIds(List.of("A1")).userId("u1").expiresAt(Instant.now().plusSeconds(100)).status(Enums.HoldStatus.ACTIVE).build();
    when(holdService.validateOwnership("h1", "u1")).thenReturn(h);
    when(bookings.save(any())).thenAnswer(i->{Booking b=i.getArgument(0); b.setId("b1"); return b;});
    var resp = service.confirm("u1", new BookingDtos.ConfirmRequest("h1","cash",null));
    assertEquals("b1", resp.bookingId());
  }
}
