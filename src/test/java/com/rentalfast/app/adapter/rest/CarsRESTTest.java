package com.rentalfast.app.adapter.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@DataJpaTest //POR EL MOMENTO **NO USARE PARTES DE JPA Y HIBERNATE** PARA ESTA PRUEBA
public class CarsRESTTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test for add a new car")
    public void addCarTest() throws Exception {

        String carJson = """
                
                {
                    "tuition" : "MW39421",
                    "nameCar" : "BMW M3",
                    "description": "German car",
                    "carColor" : "Black",
                    "originalPrice" : 1000,
                    "imageSrc" : "url/invalid",
                    "idStatus" : "2"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/cars").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(carJson)

        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test for add a new car without JSON")
    public void addCarWithoutJSON() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cars").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isBadRequest()).
                andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid JSON"));
    }



}
