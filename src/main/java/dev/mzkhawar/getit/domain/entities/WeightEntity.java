package dev.mzkhawar.getit.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
