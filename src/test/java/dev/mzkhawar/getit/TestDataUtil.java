package dev.mzkhawar.getit;

import dev.mzkhawar.getit.model.dto.WeightDto;
import dev.mzkhawar.getit.model.entities.Weight;

import java.time.LocalDateTime;

public final class TestDataUtil {

    private TestDataUtil() {}


    public static Weight createTestWeightEntityA() {
        return Weight.builder()
                .weightId(1L)
                .weightInPounds(250.2)
                .recordedOn(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static Weight createTestWeightEntityB() {
        return Weight.builder()
                .weightId(2L)
                .weightInPounds(240.4)
                .recordedOn(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static Weight createTestWeightEntityC() {
        return Weight.builder()
                .weightId(3L)
                .weightInPounds(230.3)
                .recordedOn(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightDto createTestWeightDtoA() {
        return WeightDto.builder()
                .id(1L)
                .weightInPounds(250.2)
                .recordedOn(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightDto createTestWeightDtoB() {
        return WeightDto.builder()
                .id(2L)
                .weightInPounds(240.4)
                .recordedOn(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static WeightDto createTestWeightDtoC() {
        return WeightDto.builder()
                .id(3L)
                .weightInPounds(230.3)
                .recordedOn(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }
}
