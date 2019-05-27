package com.happytown.controller;

import com.happytown.service.HabitantService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HabitantController.class)
class HabitantControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HabitantService habitantService;
/*
    @Test
    void execute() throws Exception {
        // Given
        Habitant habitant = HabitantFixture.aHabitant_sansCadeau();
        when(habitantService.getAll()).thenReturn(Lists.newArrayList(habitant));

        // When Then
        mockMvc.perform(get("/api/habitants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nom", is(habitant.getNom())));
    }
*/
}