package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document;
@Document("screens") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Screen { @Id private String id; private String cinemaId; private String name; private String seatLayoutId; }
