package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.util.*;
@Document("seat_layouts") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatLayout { @Id private String id; private int rows; private int cols; private Map<String, Enums.SeatType> seatTypes; private Set<String> blockedSeats; }
