package dev.mzkhawar.getit.domain.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TimeZoneColumn;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "weight")
public class WeightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weight_id_seq")
    private Long weightId;

    private Double weightInPounds;

    // @CreationTimestamp
    private LocalDateTime recordedAt;

}
