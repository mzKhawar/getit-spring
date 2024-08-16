package dev.mzkhawar.getit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeightDto {

    private Long id;

    private Double weightInPounds;

    private LocalDateTime recordedAt;
}
