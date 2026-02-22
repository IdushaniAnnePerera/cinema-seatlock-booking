package com.cinema.repo;
import com.cinema.domain.Promo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface PromoRepository extends MongoRepository<Promo, String> {

}
