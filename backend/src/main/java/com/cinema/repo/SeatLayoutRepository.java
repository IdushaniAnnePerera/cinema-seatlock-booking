package com.cinema.repo;
import com.cinema.domain.SeatLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface SeatLayoutRepository extends MongoRepository<SeatLayout, String> {

}
