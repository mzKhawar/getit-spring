package dev.mzkhawar.getit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weight {

    private Long weightId;
    private Double weightInPounds;
    private LocalDateTime recordedAt;

}
