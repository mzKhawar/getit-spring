package dev.mzkhawar.getit.controller;

import dev.mzkhawar.getit.model.dto.WeightDto;
import dev.mzkhawar.getit.model.entities.User;
import dev.mzkhawar.getit.model.entities.Weight;
import dev.mzkhawar.getit.mapper.Mapper;
import dev.mzkhawar.getit.service.WeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/weights")
@RequiredArgsConstructor
public class WeightController {

    private final WeightService weightService;
    private final Mapper<Weight, WeightDto> weightMapper;

    @PostMapping("")
    public ResponseEntity<WeightDto> createWeight(@RequestBody final WeightDto weight, Principal principal) {
        return new ResponseEntity<>(weightService.save(weight, principal), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<WeightDto>> getAllWeights(Principal principal) {
        return new ResponseEntity<>(weightService.findAllForUser(principal), HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<WeightDto>> getAllWeightsForDate(@PathVariable final String date, Principal principal) {
        return new ResponseEntity<>(weightService.findByCreatedOn(date, principal), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WeightDto> updateWeight(@PathVariable final Long id, @RequestBody final WeightDto weight) {
      return new ResponseEntity<>(weightService.updateWeight(id, weight), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteWeight(@PathVariable final Long id) {
        weightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
