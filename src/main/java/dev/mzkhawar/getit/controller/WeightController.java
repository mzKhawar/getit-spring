package dev.mzkhawar.getit.controller;

import dev.mzkhawar.getit.model.dto.WeightDto;
import dev.mzkhawar.getit.model.entities.Weight;
import dev.mzkhawar.getit.mapper.Mapper;
import dev.mzkhawar.getit.service.WeightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weights")
public class WeightController {

    private final WeightService weightService;

    private final Mapper<Weight, WeightDto> weightMapper;

    public WeightController(final WeightService weightService, final Mapper<Weight, WeightDto> weightMapper) {
        this.weightService = weightService;
        this.weightMapper = weightMapper;
    }

    @PostMapping("")
    public ResponseEntity<WeightDto> createWeight(@RequestBody final WeightDto weight) {
        Weight weightEntity = weightMapper.mapFrom(weight);
        Weight savedWeight = weightService.save(weightEntity);
        return new ResponseEntity<>(weightMapper.mapTo(savedWeight), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<WeightDto>> getAllWeights() {
        List<Weight> weightEntities =  weightService.findAll();
        return new ResponseEntity<>(weightEntities.stream().map(weightMapper::mapTo).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeightDto> getWeightById(@PathVariable final Long id) {
        Optional<Weight> foundWeight = weightService.findById(id);
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
        Weight weight = weightMapper.mapFrom(weightDto);
        Weight savedWeight = weightService.save(weight);
        return new ResponseEntity<>(weightMapper.mapTo(savedWeight), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<WeightDto> partialUpdateWeight(@PathVariable final Long id, @RequestBody final WeightDto weightDto) {
        if (!weightService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Weight weight = weightMapper.mapFrom(weightDto);
        Weight updatedWeight = weightService.partialUpdate(id, weight);
        return new ResponseEntity<>(weightMapper.mapTo(updatedWeight), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteWeight(@PathVariable final Long id) {
        weightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
