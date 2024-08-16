package dev.mzkhawar.getit.repositories;

import dev.mzkhawar.getit.TestDataUtil;
import dev.mzkhawar.getit.domain.entities.WeightEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WeightEntityRepositoryIntegrationTests {

    private final WeightRepository weightRepository;

    @Autowired
    public WeightEntityRepositoryIntegrationTests(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @Test
    public void testWeightCanBeCreatedAndRead() {
        WeightEntity weightEntity = TestDataUtil.createTestWeightEntityA();
        weightRepository.save(weightEntity);

        Optional<WeightEntity> result = weightRepository.findById(weightEntity.getWeightId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(weightEntity);
    }

    @Test
    public void testMutipleWeightsCanBeCreatedAndRecalled() {
        WeightEntity weightEntityA = TestDataUtil.createTestWeightEntityA();
        weightRepository.save(weightEntityA);
        WeightEntity weightEntityB = TestDataUtil.createTestWeightEntityB();
        weightRepository.save(weightEntityB);
        WeightEntity weightEntityC = TestDataUtil.createTestWeightEntityC();
        weightRepository.save(weightEntityC);

        Iterable<WeightEntity> result = weightRepository.findAll();
        assertThat(result).hasSize(3).containsExactly(weightEntityA, weightEntityB, weightEntityC);
    }

    @Test
    public void testWeightCanBeUpdated() {
        WeightEntity weightEntityA = TestDataUtil.createTestWeightEntityA();
        weightRepository.save(weightEntityA);
        weightEntityA.setWeightInPounds(300.1);
        weightRepository.save(weightEntityA);

        Optional<WeightEntity> result = weightRepository.findById(weightEntityA.getWeightId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(weightEntityA);
    }

    @Test
    public void testWeightCanBeDeleted() {
        WeightEntity weightEntityA = TestDataUtil.createTestWeightEntityA();
        weightRepository.save(weightEntityA);
        weightRepository.deleteById(weightEntityA.getWeightId());

        Optional<WeightEntity> result = weightRepository.findById(weightEntityA.getWeightId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetWeightLessThan() {
        WeightEntity weightEntityA = TestDataUtil.createTestWeightEntityA();
        weightRepository.save(weightEntityA);
        WeightEntity weightEntityB = TestDataUtil.createTestWeightEntityB();
        weightRepository.save(weightEntityB);
        WeightEntity weightEntityC = TestDataUtil.createTestWeightEntityC();
        weightRepository.save(weightEntityC);

        Iterable<WeightEntity> result = weightRepository.weightInPoundsLessThan(250);
        assertThat(result).hasSize(2).containsExactly(weightEntityB, weightEntityC);

    }

    @Test
    public void testGetWeightGreaterThan() {
        WeightEntity weightEntityA = TestDataUtil.createTestWeightEntityA();
        weightRepository.save(weightEntityA);
        WeightEntity weightEntityB = TestDataUtil.createTestWeightEntityB();
        weightRepository.save(weightEntityB);
        WeightEntity weightEntityC = TestDataUtil.createTestWeightEntityC();
        weightRepository.save(weightEntityC);

        Iterable<WeightEntity> result = weightRepository.findWeightGreaterThan(250);
        assertThat(result).hasSize(1).containsExactly(weightEntityA);
    }
}
