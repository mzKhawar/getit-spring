package dev.mzkhawar.getit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mzkhawar.getit.TestDataUtil;
import dev.mzkhawar.getit.model.dto.WeightDto;
import dev.mzkhawar.getit.model.entities.Weight;
import dev.mzkhawar.getit.service.WeightService;
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
        WeightDto testWeightA = TestDataUtil.createTestWeightDtoA();
        testWeightA.setId(null);
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
        WeightDto testWeightA = TestDataUtil.createTestWeightDtoA();
        testWeightA.setId(null);
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
        Weight weightA = TestDataUtil.createTestWeightEntityA();
        weightService.save(weightA);
        Weight weightB = TestDataUtil.createTestWeightEntityB();
        weightService.save(weightB);
        Weight weightC = TestDataUtil.createTestWeightEntityC();
        weightService.save(weightC);

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
        Weight weightA = TestDataUtil.createTestWeightEntityA();
        weightService.save(weightA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/weights/1")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testGetWeightByIdReturnsStatus404WhenNotExists() throws Exception {

        Weight weightA = TestDataUtil.createTestWeightEntityA();
        weightService.save(weightA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/weights/99")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testGetWeightByIdReturnsWeight() throws Exception {
        Weight testWeightA = TestDataUtil.createTestWeightEntityA();
        weightService.save(testWeightA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/weights/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.weightInPounds").value("250.2")
        );
    }

    @Test
    public void testFullUpdateWeightReturnsStatus200WhenExists() throws Exception {
        Weight testWeightA = TestDataUtil.createTestWeightEntityA();
        weightService.save(testWeightA);
        String weightJson = objectMapper.writeValueAsString(testWeightA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/weights/" + testWeightA.getWeightId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(weightJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testFullUpdateWeightReturnsStatus404WhenNotExists() throws Exception {
        WeightDto testWeightA = TestDataUtil.createTestWeightDtoA();
        String weightJson = objectMapper.writeValueAsString(testWeightA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/weights/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(weightJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testPartialUpdateWeightReturnsStatus200WhenExists() throws Exception {
        Weight testWeightA = TestDataUtil.createTestWeightEntityA();
        Weight savedWeightA = weightService.save(testWeightA);

        WeightDto testWeightDtoA = TestDataUtil.createTestWeightDtoA();
        testWeightA.setWeightInPounds(111.11);
        String weightJson = objectMapper.writeValueAsString(testWeightDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/weights/" + savedWeightA.getWeightId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(weightJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testPartialUpdateWeightReturnsUpdatedWeight() throws Exception {
        Weight testWeightA = TestDataUtil.createTestWeightEntityA();
        Weight savedWeightA = weightService.save(testWeightA);

        WeightDto testWeightDtoA = TestDataUtil.createTestWeightDtoA();
        testWeightDtoA.setWeightInPounds(111.11);
        String weightJson = objectMapper.writeValueAsString(testWeightDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/weights/" + savedWeightA.getWeightId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(weightJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedWeightA.getWeightId())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.weightInPounds").value("111.11")
        );
    }

    @Test
    public void testDeleteWeightReturnsStatus204ForWeightNotExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/weights/99")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testDeleteWeightReturnsStatus204ForWeightExists() throws Exception {
        Weight testWeightA = TestDataUtil.createTestWeightEntityA();
        Weight savedWeightA = weightService.save(testWeightA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/weights/" + savedWeightA.getWeightId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }


}
