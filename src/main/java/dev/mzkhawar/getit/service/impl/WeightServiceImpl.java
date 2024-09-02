package dev.mzkhawar.getit.service.impl;

import dev.mzkhawar.getit.mapper.Mapper;
import dev.mzkhawar.getit.model.dto.WeightDto;
import dev.mzkhawar.getit.model.entities.User;
import dev.mzkhawar.getit.model.entities.Weight;
import dev.mzkhawar.getit.repository.UserRepository;
import dev.mzkhawar.getit.repository.WeightRepository;
import dev.mzkhawar.getit.service.WeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeightServiceImpl implements WeightService {

    private final WeightRepository weightRepository;
    private final UserRepository userRepository;
    private final Mapper<Weight, WeightDto> weightMapper;

    @Override
    public WeightDto save(WeightDto weight, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        Weight weightEntity = weightMapper.mapFrom(weight);
        weightEntity.setUser(user);
        weightRepository.save(weightEntity);
        return weightMapper.mapTo(weightEntity);
    }

    @Override
    public Optional<Weight> findById(Long id) {
        return weightRepository.findById(id);
    }

    @Override
    public List<WeightDto> findAllForUser(Principal principal) {
        List<Weight> weightEntities = weightRepository.findWeightByUsername(principal.getName());
        return weightEntities.stream().map(weightMapper::mapTo).toList();
    }

    @Override
    public List<WeightDto> findByCreatedOn(String date, Principal principal) {
        LocalDate localDate = LocalDate.parse(date);
        List<Weight> weightEntities = weightRepository.findByCreatedOnAndUsername(localDate, principal.getName());
        return weightEntities.stream().map(weightMapper::mapTo).toList();
    }

    @Override
    public boolean exists(Long id) {
        return weightRepository.existsById(id);
    }

    @Override
    public WeightDto updateWeight(Long id, WeightDto weight) {
        weight.setId(id);
        var weightEntity = weightRepository.findById(id).map(existingWeight -> {
            Optional.ofNullable(weight.getWeightInPounds()).ifPresent(existingWeight::setWeightInPounds);
            Optional.ofNullable(weight.getRecordedOn()).ifPresent(existingWeight::setRecordedOn);
            return weightRepository.save(existingWeight);
        }).orElseThrow(() -> new RuntimeException("Weight not found"));
        return weightMapper.mapTo(weightRepository.save(weightEntity));
    }

    @Override
    public void delete(Long id) {
        weightRepository.deleteById(id);
    }


}
