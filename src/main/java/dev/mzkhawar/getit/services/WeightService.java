package dev.mzkhawar.getit.services;

import dev.mzkhawar.getit.domain.entities.WeightEntity;

import java.util.List;
import java.util.Optional;

public interface WeightService {

    WeightEntity save(WeightEntity weightEntity);

    List<WeightEntity> findAll();

    Optional<WeightEntity> findById(Long id);

    boolean isExists(Long id);

    WeightEntity partialUpdate(Long id, WeightEntity weightEntity);

    void delete(Long id);
}
