package com.cinema.service;

import com.cinema.domain.*;
import com.cinema.dto.BookingDtos;
import com.cinema.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class BookingService {
  private final SeatHoldService seatHoldService;
  private final BookingRepository bookingRepository;
  private final PaymentRepository paymentRepository;
  private final TicketRepository ticketRepository;
  private final SeatHoldRepository seatHoldRepository;

  public BookingDtos.HoldResponse hold(String userId, BookingDtos.HoldRequest req) {
    var hold = seatHoldService.createHold(userId, req);
    return new BookingDtos.HoldResponse(hold.getId(), hold.getShowtimeId(), hold.getSeatIds(), hold.getExpiresAt().toString());
  }

  public BookingDtos.BookingResponse confirm(String userId, BookingDtos.ConfirmRequest req) {
    var hold = seatHoldService.validateOwnership(req.holdId(), userId);
    double amount = hold.getSeatIds().size() * 12.5;
    var booking = bookingRepository.save(Booking.builder().userId(userId).showtimeId(hold.getShowtimeId()).seats(hold.getSeatIds()).createdAt(Instant.now()).amount(amount).status(Enums.BookingStatus.CONFIRMED).build());
    paymentRepository.save(Payment.builder().bookingId(booking.getId()).provider(req.paymentMethod()).status(Enums.PaymentStatus.PAID).ref("SIM-" + UUID.randomUUID()).build());
    var token = UUID.randomUUID()+"."+booking.getId();
    ticketRepository.save(Ticket.builder().bookingId(booking.getId()).qrToken(token).build());
    hold.setStatus(Enums.HoldStatus.CONVERTED); seatHoldRepository.save(hold);
    return new BookingDtos.BookingResponse(booking.getId(), booking.getStatus().name(), amount, token);
  }

  public String checkin(String token) {
    var ticket = ticketRepository.findByQrToken(token).orElseThrow();
    if (ticket.getCheckedInAt() != null) throw new RuntimeException("Already checked in");
    ticket.setCheckedInAt(Instant.now());
    ticketRepository.save(ticket);
    return "CHECKED_IN";
  }
}
