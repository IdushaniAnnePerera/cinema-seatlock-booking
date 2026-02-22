package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant; import java.util.*;
@Document("bookings") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Booking { @Id private String id; private String userId; private String showtimeId; private List<String> seats; private double amount; private Enums.BookingStatus status; private Instant createdAt; }
