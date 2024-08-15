package dev.mzkhawar.getit.dao.impl;

import dev.mzkhawar.getit.dao.WeightDao;
import dev.mzkhawar.getit.domain.Weight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class WeightDaoImpl implements WeightDao {

    private final JdbcTemplate jdbcTemplate;

    public WeightDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Weight weight) {
        jdbcTemplate.update("INSERT INTO weight (weight_id, weight_in_pounds, recorded_at) VALUES (?, ?, ?)",
                weight.getWeightId(), weight.getWeightInPounds(), weight.getRecordedAt());
    }

    @Override
    public Optional<Weight> findOne(long weightId) {
        List<Weight> results = jdbcTemplate.query("SELECT weight_id, weight_in_pounds, recorded_at FROM weight WHERE weight_id = ? LIMIT 1",
               new WeightRowMapper(), weightId);

        return results.stream().findFirst();
    }

    @Override
    public List<Weight> find() {
        return jdbcTemplate.query("SELECT weight_id, weight_in_pounds, recorded_at FROM weight",
            new WeightRowMapper()
        );
    }

    @Override
    public void update(long id, Weight weight) {
        jdbcTemplate.update(
                "UPDATE weight SET weight_id = ?, weight_in_pounds = ?, recorded_at = ? WHERE weight_id = ?",
                weight.getWeightId(), weight.getWeightInPounds(), weight.getRecordedAt(), id
        );
    }

    @Override
    public void delete(long l) {
        jdbcTemplate.update("DELETE FROM weight WHERE weight_id = ?",
                1L);

    }

    public static class WeightRowMapper implements RowMapper<Weight> {
        @Override
        public Weight mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Weight.builder()
                    .weightId(rs.getLong("weight_id"))
                    .weightInPounds(rs.getDouble("weight_in_pounds"))
                    .recordedAt(rs.getTimestamp("recorded_at").toLocalDateTime())
                    .build();
        }
    }


}
