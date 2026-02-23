package com.cinema.repo;
import com.cinema.domain.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface BookingRepository extends MongoRepository<Booking, String> {
  List<Booking> findByUserId(String userId);
}

