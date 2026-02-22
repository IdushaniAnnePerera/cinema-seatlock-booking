package com.cinema.repo;
import com.cinema.domain.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface TicketRepository extends MongoRepository<Ticket, String> {
  Optional<Ticket> findByQrToken(String qrToken);
  Optional<Ticket> findByBookingId(String bookingId);
}

