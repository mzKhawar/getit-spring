package dev.mzkhawar.getit.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Weight {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Weight is required")
    private Double weightInPounds;

    @NotNull(message = "Created on date required")
    private LocalDate recordedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
