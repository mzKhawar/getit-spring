package dev.mzkhawar.getit.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mzkhawar.getit.TestDataUtil;
import dev.mzkhawar.getit.domain.entities.WeightEntity;
import dev.mzkhawar.getit.services.WeightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class WeightControllerIntegrationTests {

    private WeightService weightService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public WeightControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, WeightService weightService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.weightService = weightService;
    }

    @Test
    public void testCreateWeightReturns201Status() throws Exception {
        WeightEntity testWeightA = TestDataUtil.createTestWeightA();
        testWeightA.setWeightId(null);
        String weightJson = objectMapper.writeValueAsString(testWeightA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/weights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(weightJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testCreateWeightReturnsSavedWeight() throws Exception {
        WeightEntity testWeightA = TestDataUtil.createTestWeightA();
        testWeightA.setWeightId(null);
        String weightJson = objectMapper.writeValueAsString(testWeightA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/weights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(weightJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.weightInPounds").value("250.2")
        );
    }

    @Test
    public void testGetAllWeightsReturnsStatus200() throws Exception {
        WeightEntity weightA = TestDataUtil.createTestWeightA();
        weightService.createWeight(weightA);
        WeightEntity weightB = TestDataUtil.createTestWeightB();
        weightService.createWeight(weightB);
        WeightEntity weightC = TestDataUtil.createTestWeightC();
        weightService.createWeight(weightC);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/weights")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].weightInPounds").value("250.2")
        );
    }

    @Test
    public void testGetWeightByIdReturnsStatus200WhenExists() throws Exception {

        WeightEntity weightA = TestDataUtil.createTestWeightA();
        weightService.createWeight(weightA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/weights/1")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testGetWeightByIdReturnsStatus404WhenNotExists() throws Exception {

        WeightEntity weightA = TestDataUtil.createTestWeightA();
        weightService.createWeight(weightA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/weights/99")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testGetWeightByIdReturnsWeight() throws Exception {
        WeightEntity testWeightA = TestDataUtil.createTestWeightA();
        weightService.createWeight(testWeightA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/weights/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.weightInPounds").value("250.2")
        );
    }


}
