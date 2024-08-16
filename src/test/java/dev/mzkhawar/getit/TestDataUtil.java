package dev.mzkhawar.getit;

import dev.mzkhawar.getit.domain.dto.WeightDto;
import dev.mzkhawar.getit.domain.entities.WeightEntity;

import java.time.LocalDateTime;

public final class TestDataUtil {

    private TestDataUtil() {}


    public static WeightEntity createTestWeightEntityA() {
        return WeightEntity.builder()
                .weightId(1L)
                .weightInPounds(250.2)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightEntity createTestWeightEntityB() {
        return WeightEntity.builder()
                .weightId(2L)
                .weightInPounds(240.4)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightEntity createTestWeightEntityC() {
        return WeightEntity.builder()
                .weightId(3L)
                .weightInPounds(230.3)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightDto createTestWeightDtoA() {
        return WeightDto.builder()
                .id(1L)
                .weightInPounds(250.2)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightDto createTestWeightDtoB() {
        return WeightDto.builder()
                .id(2L)
                .weightInPounds(240.4)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightDto createTestWeightDtoC() {
        return WeightDto.builder()
                .id(3L)
                .weightInPounds(230.3)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }
}
