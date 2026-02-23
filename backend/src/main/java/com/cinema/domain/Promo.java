package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("promos") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Promo { @Id private String id; private String code; private double percentOff; private Instant validFrom; private Instant validTo; private int usageLimit; private int usedCount; }
