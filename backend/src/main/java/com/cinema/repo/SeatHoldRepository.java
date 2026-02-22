package com.cinema.repo;
import com.cinema.domain.SeatHold;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface SeatHoldRepository extends MongoRepository<SeatHold, String> {
  List<SeatHold> findByShowtimeIdAndStatus(String showtimeId, com.cinema.domain.Enums.HoldStatus status);
  List<SeatHold> findByStatusAndExpiresAtBefore(com.cinema.domain.Enums.HoldStatus status, java.time.Instant cutoff);
}

