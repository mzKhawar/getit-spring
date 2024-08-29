package dev.mzkhawar.getit.service;

import dev.mzkhawar.getit.model.entities.Weight;

import java.util.List;
import java.util.Optional;

public interface WeightService {

    Weight save(Weight weight);

    List<Weight> findAll();

    Optional<Weight> findById(Long id);

    boolean exists(Long id);

    Weight partialUpdate(Long id, Weight weight);

    void delete(Long id);
}
