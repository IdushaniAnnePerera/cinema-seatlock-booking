package com.cinema.domain;

public class Enums {
  public enum Role { CUSTOMER, ADMIN, STAFF }
  public enum SeatState { AVAILABLE, HELD, BOOKED, BLOCKED }
  public enum SeatType { REGULAR, PREMIUM, COUPLE, BALCONY }
  public enum BookingStatus { PENDING, CONFIRMED, CANCELLED, REFUNDED }
  public enum PaymentStatus { CREATED, PAID, FAILED }
  public enum HoldStatus { ACTIVE, EXPIRED, RELEASED, CONVERTED }
}
