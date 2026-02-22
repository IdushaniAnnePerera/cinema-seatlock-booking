package com.cinema.repo;
import com.cinema.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface MovieRepository extends MongoRepository<Movie, String> {
  List<Movie> findByNowShowing(boolean nowShowing);
}

