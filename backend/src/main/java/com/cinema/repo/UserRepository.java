package com.cinema.repo;
import com.cinema.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByEmail(String email);
}

