package com.cinema.repo;
import com.cinema.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
