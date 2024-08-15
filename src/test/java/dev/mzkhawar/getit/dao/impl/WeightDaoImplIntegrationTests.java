package dev.mzkhawar.getit.dao.impl;

import dev.mzkhawar.getit.TestDataUtil;
import dev.mzkhawar.getit.domain.Weight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WeightDaoImplIntegrationTests {

    private WeightDaoImpl weightDaoImpl;

    @Autowired
    public WeightDaoImplIntegrationTests (WeightDaoImpl weightDaoImpl) {
        this.weightDaoImpl = weightDaoImpl;
    }

    @Test
    public void testWeightCanBeCreatedAndRecalled() {
        Weight weight = TestDataUtil.createTestWeightA();
        weightDaoImpl.create(weight);
        Optional<Weight> result = weightDaoImpl.findOne(weight.getWeightId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(weight);
    }

    @Test
    public void testMutipleWeightsCanBeCreatedAndRecalled() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightDaoImpl.create(weightA);
        Weight weightB = TestDataUtil.createTestWeightB();
        weightDaoImpl.create(weightB);
        Weight weightC = TestDataUtil.createTestWeightC();
        weightDaoImpl.create(weightC);

        List<Weight> result = weightDaoImpl.find();
        assertThat(result).hasSize(3).containsExactly(weightA, weightB, weightC);
    }

    @Test
    public void testWeightCanBeUpdated() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightDaoImpl.create(weightA);
        weightA.setWeightInPounds(300.1);
        weightDaoImpl.update(weightA.getWeightId(), weightA);
        Optional<Weight> result = weightDaoImpl.findOne(weightA.getWeightId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(weightA);
    }

    @Test
    public void testWeightCanBeDeleted() {
        Weight weightA = TestDataUtil.createTestWeightA();
        weightDaoImpl.create(weightA);
        weightDaoImpl.delete(weightA.getWeightId());
        Optional<Weight> result = weightDaoImpl.findOne(weightA.getWeightId());
        assertThat(result).isEmpty();
    }
}
