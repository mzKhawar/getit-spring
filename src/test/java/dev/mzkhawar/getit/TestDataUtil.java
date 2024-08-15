package dev.mzkhawar.getit;

import dev.mzkhawar.getit.domain.Weight;

import java.time.LocalDateTime;

public final class TestDataUtil {

    private TestDataUtil() {}


    public static Weight createTestWeightA() {
        return Weight.builder()
                .weightId(1L)
                .weightInPounds(250.2)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static Weight createTestWeightB() {
        return Weight.builder()
                .weightId(2L)
                .weightInPounds(240.4)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }

    public static Weight createTestWeightC() {
        return Weight.builder()
                .weightId(3L)
                .weightInPounds(230.3)
                .recordedAt(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
                .build();
    }
}
