package dev.mzkhawar.getit.service.impl;

import dev.mzkhawar.getit.model.entities.Weight;
import dev.mzkhawar.getit.repository.WeightRepository;
import dev.mzkhawar.getit.service.WeightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class WeightServiceImpl implements WeightService {

    private final WeightRepository weightRepository;

    public WeightServiceImpl(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @Override
    public Weight save(Weight weight) {
        return weightRepository.save(weight);
    }

    @Override
    public List<Weight> findAll() {
        return StreamSupport.stream(weightRepository
                .findAll()
                .spliterator(),
                        false)
                .toList();
    }

    @Override
    public Optional<Weight> findById(Long id) {
        return weightRepository.findById(id);
    }

    @Override
    public boolean exists(Long id) {
        return weightRepository.existsById(id);
    }

    @Override
    public Weight partialUpdate(Long id, Weight weight) {
        weight.setWeightId(id);
        return weightRepository.findById(id).map(existingWeight -> {
            Optional.ofNullable(weight.getWeightInPounds()).ifPresent(existingWeight::setWeightInPounds);
            Optional.ofNullable(weight.getRecordedOn()).ifPresent(existingWeight::setRecordedOn);
            return weightRepository.save(existingWeight);
        }).orElseThrow(() -> new RuntimeException("Weight not found"));
    }

    @Override
    public void delete(Long id) {
        weightRepository.deleteById(id);
    }
}
