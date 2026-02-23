package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document;
@Document("cinemas") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Cinema { @Id private String id; private String name; private String location; }
