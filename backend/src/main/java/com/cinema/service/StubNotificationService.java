package com.cinema.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class StubNotificationService implements NotificationService {
  public void sendTicketEmail(String email, String bookingId, String ticketToken){ log.info("MAILTRAP/STUB email={} booking={} token={}", email, bookingId, ticketToken); }
  public void sendSms(String phone, String message){ log.info("TWILIO/STUB sms {} {}", phone, message); }
}
