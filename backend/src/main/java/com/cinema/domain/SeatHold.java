package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant; import java.util.*;
@Document("seat_holds") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatHold { @Id private String id; private String showtimeId; private List<String> seatIds; private String userId; private Instant expiresAt; private Enums.HoldStatus status; }
