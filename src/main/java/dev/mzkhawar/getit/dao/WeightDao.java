package dev.mzkhawar.getit.dao;

import dev.mzkhawar.getit.domain.Weight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface WeightDao {
    void create(Weight weight);

    Optional<Weight> findOne(long l);

    List<Weight> find();

    void update(long id, Weight weight);

    void delete(long l);
}
