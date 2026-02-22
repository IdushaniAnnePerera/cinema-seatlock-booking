package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document;
@Document("payments") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment { @Id private String id; private String bookingId; private String provider; private Enums.PaymentStatus status; private String ref; }
