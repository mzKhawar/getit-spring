package dev.mzkhawar.getit.domain;

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
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weight_id_seq")
    private Long weightId;

    private Double weightInPounds;

    private LocalDateTime recordedAt;

}
