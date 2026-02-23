package com.cinema.service;

public interface NotificationService {
  void sendTicketEmail(String email, String bookingId, String ticketToken);
  void sendSms(String phone, String message);
}
