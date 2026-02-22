package com.cinema.repo;
import com.cinema.domain.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface CinemaRepository extends MongoRepository<Cinema, String> {

}
