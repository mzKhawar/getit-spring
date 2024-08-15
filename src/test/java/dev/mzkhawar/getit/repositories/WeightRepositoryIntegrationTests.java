package dev.mzkhawar.getit.repositories;

import dev.mzkhawar.getit.TestDataUtil;
import dev.mzkhawar.getit.domain.Weight;
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
public class WeightRepositoryIntegrationTests {

    private final WeightRepository weightRepository;

    @Autowired
    public WeightRepositoryIntegrationTests (WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @Test
    public void testWeightCanBeCreatedAndRead() {
        Weight weight = TestDataUtil.createTestWeightA();
        weightRepository.save(weight);

        Optional<Weight> result = weightRepository.findById(weight.getWeightId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(weight);
    }

    @Test
    public void testMutipleWeightsCanBeCreatedAndRecalled() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightRepository.save(weightA);
        Weight weightB = TestDataUtil.createTestWeightB();
        weightRepository.save(weightB);
        Weight weightC = TestDataUtil.createTestWeightC();
        weightRepository.save(weightC);

        Iterable<Weight> result = weightRepository.findAll();
        assertThat(result).hasSize(3).containsExactly(weightA, weightB, weightC);
    }

    @Test
    public void testWeightCanBeUpdated() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightRepository.save(weightA);
        weightA.setWeightInPounds(300.1);
        weightRepository.save(weightA);

        Optional<Weight> result = weightRepository.findById(weightA.getWeightId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(weightA);
    }

    @Test
    public void testWeightCanBeDeleted() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightRepository.save(weightA);
        weightRepository.deleteById(weightA.getWeightId());

        Optional<Weight> result = weightRepository.findById(weightA.getWeightId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetWeightLessThan() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightRepository.save(weightA);
        Weight weightB = TestDataUtil.createTestWeightB();
        weightRepository.save(weightB);
        Weight weightC = TestDataUtil.createTestWeightC();
        weightRepository.save(weightC);

        Iterable<Weight> result = weightRepository.weightInPoundsLessThan(250);
        assertThat(result).hasSize(2).containsExactly(weightB, weightC);

    }

    @Test
    public void testGetWeightGreaterThan() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightRepository.save(weightA);
        Weight weightB = TestDataUtil.createTestWeightB();
        weightRepository.save(weightB);
        Weight weightC = TestDataUtil.createTestWeightC();
        weightRepository.save(weightC);

        Iterable<Weight> result = weightRepository.findWeightGreaterThan(250);
        assertThat(result).hasSize(1).containsExactly(weightA);
    }
}
