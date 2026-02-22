package com.cinema.repo;
import com.cinema.domain.Showtime;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface ShowtimeRepository extends MongoRepository<Showtime, String> {
  List<Showtime> findByMovieId(String movieId);
}

