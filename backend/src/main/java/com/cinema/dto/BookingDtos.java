package com.cinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BookingDtos {
  public record HoldRequest(@NotBlank String showtimeId, @NotEmpty List<String> seatIds) {}
  public record HoldResponse(String holdId, String showtimeId, List<String> seatIds, String expiresAt) {}
  public record ConfirmRequest(@NotBlank String holdId, @NotBlank String paymentMethod, String promoCode) {}
  public record BookingResponse(String bookingId, String status, double amount, String ticketToken) {}
}
