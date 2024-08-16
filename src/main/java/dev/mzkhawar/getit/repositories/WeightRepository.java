package dev.mzkhawar.getit.repositories;

import dev.mzkhawar.getit.domain.entities.WeightEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends CrudRepository<WeightEntity, Long> {

    Iterable<WeightEntity> weightInPoundsLessThan(int age);

    Iterable<WeightEntity> weightInPoundsGreaterThan(int age);

    @Query("SELECT w from WeightEntity w where w.weightInPounds > ?1")
    Iterable<WeightEntity> findWeightGreaterThan(int age);
}
