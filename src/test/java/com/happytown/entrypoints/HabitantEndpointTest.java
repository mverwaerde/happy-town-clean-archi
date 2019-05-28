package com.happytown.entrypoints;

import com.happytown.core.entities.Habitant;
import com.happytown.core.use_cases.GetAllHabitants;
import com.happytown.entrypoints.rest.HabitantEndpoint;
import com.happytown.fixtures.HabitantFixture;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HabitantEndpoint.class)
public class HabitantEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllHabitants getAllHabitants;

    @Test
    public void shouldReturnAllHabitants() throws Exception {
        // Arrange
        Habitant habitant = HabitantFixture.aHabitant_avecCadeau();

        Mockito.doReturn(List.of(habitant)).when(getAllHabitants).execute();
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/habitants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(habitant.getId())))
                .andExpect(jsonPath("$[0].nom", is(habitant.getNom())))
                .andExpect(jsonPath("$[0].prenom", is(habitant.getPrenom())))
                .andExpect(jsonPath("$[0].email", is(habitant.getEmail())))
                .andExpect(jsonPath("$[0].dateNaissance", is(String.valueOf(habitant.getDateNaissance()))))
                .andExpect(jsonPath("$[0].dateArriveeCommune", is(String.valueOf(habitant.getDateArriveeCommune()))))
                .andExpect(jsonPath("$[0].adressePostale", is(habitant.getAdressePostale())))
                .andExpect(jsonPath("$[0].cadeauOffert", is(habitant.getCadeauOffert())))
                .andExpect(jsonPath("$[0].dateAttributionCadeau", is(String.valueOf(habitant.getDateAttributionCadeau()))));
    }

}