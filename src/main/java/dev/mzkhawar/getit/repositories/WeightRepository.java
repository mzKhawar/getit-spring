package dev.mzkhawar.getit.repositories;

import dev.mzkhawar.getit.domain.Weight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends CrudRepository<Weight, Long> {
}
