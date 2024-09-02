package dev.mzkhawar.getit.service;

import dev.mzkhawar.getit.model.dto.WeightDto;
import dev.mzkhawar.getit.model.entities.Weight;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface WeightService {

    WeightDto save(WeightDto weight, Principal principal);

    Optional<Weight> findById(Long id);

    boolean exists(Long id);

    void delete(Long id);

    List<WeightDto> findAllForUser(Principal principal);

    List<WeightDto> findByCreatedOn(String date, Principal principal);

    WeightDto updateWeight(Long id, WeightDto weight);
}
