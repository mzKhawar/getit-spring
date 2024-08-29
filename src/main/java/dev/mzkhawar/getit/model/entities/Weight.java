package dev.mzkhawar.getit.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weight_id_seq")
    private Long weightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Weight is required")
    private Double weightInPounds;

    //@NotNull(message = "Recorded on Date, Time is required")
    private LocalDateTime recordedOn;

}
