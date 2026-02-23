package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("tickets") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Ticket { @Id private String id; private String bookingId; private String qrToken; private Instant checkedInAt; }
