package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant; import java.util.*;
@Document("showtimes") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Showtime { @Id private String id; private String movieId; private String screenId; private Instant startTime; private Map<Enums.SeatType, Double> pricingRules; }
