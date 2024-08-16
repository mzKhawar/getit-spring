package dev.mzkhawar.getit.repository;

import dev.mzkhawar.getit.model.entities.Weight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends CrudRepository<Weight, Long> {

    Iterable<Weight> weightInPoundsLessThan(int age);

    Iterable<Weight> weightInPoundsGreaterThan(int age);

    @Query("SELECT w from Weight w where w.weightInPounds > ?1")
    Iterable<Weight> findWeightGreaterThan(int age);
}
