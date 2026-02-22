package com.cinema.repo;
import com.cinema.domain.AuditEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface AuditEventRepository extends MongoRepository<AuditEvent, String> {

}
