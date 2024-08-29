package dev.mzkhawar.getit.repository;

import dev.mzkhawar.getit.model.entities.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

    List<Weight> findWeightsByRecordedOn(LocalDateTime recordedOn);

    List<Weight> findWeightsByRecordedOnBefore(LocalDateTime recordedOn);

    List<Weight> findWeightByRecordedOnAfter(LocalDateTime recordedOn);

    List<Weight> findWeightByUserId(Long userid);

    List<Weight> weightInPoundsLessThan(int i);
}
