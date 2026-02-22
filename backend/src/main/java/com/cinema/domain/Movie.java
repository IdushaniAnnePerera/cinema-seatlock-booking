package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant; import java.util.*;
@Document("movies") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Movie { @Id private String id; private String title; private String synopsis; private List<String> genres; private String language; private String posterUrl; private String trailerYoutubeId; private Instant releaseDate; private boolean nowShowing; }
