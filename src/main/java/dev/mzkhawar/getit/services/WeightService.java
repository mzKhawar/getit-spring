package dev.mzkhawar.getit.services;

import dev.mzkhawar.getit.domain.entities.WeightEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface WeightService {

    WeightEntity createWeight(WeightEntity weightEntity);

    List<WeightEntity> findAll();

    Optional<WeightEntity> findById(Long id);
}
