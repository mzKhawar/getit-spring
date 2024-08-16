package dev.mzkhawar.getit.services.impl;

import dev.mzkhawar.getit.domain.entities.WeightEntity;
import dev.mzkhawar.getit.repositories.WeightRepository;
import dev.mzkhawar.getit.services.WeightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WeightServiceImpl implements WeightService {

    private WeightRepository weightRepository;

    public WeightServiceImpl(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @Override
    public WeightEntity createWeight(WeightEntity weightEntity) {
        return weightRepository.save(weightEntity);
    }

    @Override
    public List<WeightEntity> findAll() {
        return StreamSupport.stream(weightRepository
                .findAll()
                .spliterator(),
                        false)
                .toList();
    }

    @Override
    public Optional<WeightEntity> findById(Long id) {
        return weightRepository.findById(id);
    }
}
