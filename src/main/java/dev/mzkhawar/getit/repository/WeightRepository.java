package dev.mzkhawar.getit.repository;

import dev.mzkhawar.getit.model.entities.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {


    @Query("select w from Weight w where w.user.username = :username ")
    List<Weight> findWeightByUsername(@Param("username") String username);

    @Query(value = "select w From Weight w where w.user.username = :username and w.recordedOn = :recordedOn")
    List<Weight> findByCreatedOnAndUsername(@Param("recordedOn") LocalDate recordedOn, @Param("username") String username);
}
