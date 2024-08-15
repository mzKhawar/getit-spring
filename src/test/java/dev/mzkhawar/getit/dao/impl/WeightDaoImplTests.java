package dev.mzkhawar.getit.dao.impl;

import dev.mzkhawar.getit.TestDataUtil;
import dev.mzkhawar.getit.domain.Weight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WeightDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    WeightDaoImpl weightDaoImpl;

    @Test
    public void testCreateWeightGeneratesCorrectSql() {
        Weight weight = TestDataUtil.createTestWeightA();
        weightDaoImpl.create(weight);

        verify(jdbcTemplate).update(
                eq("INSERT INTO weight (weight_id, weight_in_pounds, recorded_at) VALUES (?, ?, ?)"),
                eq(1L), eq(250.2), eq(LocalDateTime.of(2024, 8, 15, 15, 55, 22))
        );
    }

    @Test
    public void testFindOneGeneratesCorrectSql() {
        weightDaoImpl.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT weight_id, weight_in_pounds, recorded_at FROM weight WHERE weight_id = ? LIMIT 1"),
                ArgumentMatchers.<WeightDaoImpl.WeightRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testFindManyGeneratesCorrectSql() {
        weightDaoImpl.find();
        verify(jdbcTemplate).query(
                eq("SELECT weight_id, weight_in_pounds, recorded_at FROM weight"),
                ArgumentMatchers.<WeightDaoImpl.WeightRowMapper>any());
    }

    @Test
    public void testUpdateGeneratesCorrectSql() {
        Weight weight = TestDataUtil.createTestWeightA();
        weightDaoImpl.update(weight.getWeightId(), weight);

        verify(jdbcTemplate).update(
                "UPDATE weight SET weight_id = ?, weight_in_pounds = ?, recorded_at = ? WHERE weight_id = ?",
                1L, 250.2, LocalDateTime.of(2024, 8, 15, 15, 55, 22), 1L
        );
    }

    @Test
    public void testDeleteGeneratesCorrectSql() {
        weightDaoImpl.delete(1L);
        verify(jdbcTemplate).update(
                "DELETE FROM weight WHERE weight_id = ?",
                1L
        );
    }
}
