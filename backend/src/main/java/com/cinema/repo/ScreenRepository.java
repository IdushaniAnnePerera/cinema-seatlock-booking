package com.cinema.repo;
import com.cinema.domain.Screen;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface ScreenRepository extends MongoRepository<Screen, String> {

}
