package com.cinema.domain;
import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("audit_events") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AuditEvent { @Id private String id; private String actorId; private String action; private String meta; private Instant createdAt; }
