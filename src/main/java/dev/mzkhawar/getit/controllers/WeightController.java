package dev.mzkhawar.getit.controllers;

import dev.mzkhawar.getit.domain.dto.WeightDto;
import dev.mzkhawar.getit.domain.entities.WeightEntity;
import dev.mzkhawar.getit.mappers.Mapper;
import dev.mzkhawar.getit.services.WeightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weights")
public class WeightController {

    private final WeightService weightService;

    private final Mapper<WeightEntity, WeightDto> weightMapper;

    public WeightController(final WeightService weightService, final Mapper<WeightEntity, WeightDto> weightMapper) {
        this.weightService = weightService;
        this.weightMapper = weightMapper;
    }

    @PostMapping("")
    public ResponseEntity<WeightDto> createWeight(@RequestBody final WeightDto weight) {
        WeightEntity weightEntity = weightMapper.mapFrom(weight);
        WeightEntity savedWeightEntity = weightService.save(weightEntity);
        return new ResponseEntity<>(weightMapper.mapTo(savedWeightEntity), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<WeightDto>> getAllWeights() {
        List<WeightEntity> weightEntities =  weightService.findAll();
        return new ResponseEntity<>(weightEntities.stream().map(weightMapper::mapTo).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeightDto> getWeightById(@PathVariable final Long id) {
        Optional<WeightEntity> foundWeight = weightService.findById(id);
        return foundWeight.map(weightEntity -> {
            WeightDto weightDto = weightMapper.mapTo(weightEntity);
            return new ResponseEntity<>(weightDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeightDto> updateWeight(@PathVariable final Long id, @RequestBody final WeightDto weightDto) {
        if (!weightService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        weightDto.setId(id);
        WeightEntity weightEntity = weightMapper.mapFrom(weightDto);
        WeightEntity savedWeightEntity = weightService.save(weightEntity);
        return new ResponseEntity<>(weightMapper.mapTo(savedWeightEntity), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<WeightDto> partialUpdateWeight(@PathVariable final Long id, @RequestBody final WeightDto weightDto) {
        if (!weightService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        WeightEntity weightEntity = weightMapper.mapFrom(weightDto);
        WeightEntity updatedWeight = weightService.partialUpdate(id, weightEntity);
        return new ResponseEntity<>(weightMapper.mapTo(updatedWeight), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteWeight(@PathVariable final Long id) {
        weightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
