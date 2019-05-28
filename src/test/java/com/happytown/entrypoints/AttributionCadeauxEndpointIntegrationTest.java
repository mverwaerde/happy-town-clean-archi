package com.happytown.entrypoints;

import com.happytown.core.use_cases.AttribuerCadeaux;
import com.happytown.entrypoints.rest.AttributionCadeauxEndpoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AttributionCadeauxEndpoint.class)
class AttributionCadeauxEndpointIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AttribuerCadeaux attribuerCadeaux;

    @Test
    void attribuerCadeaux() throws Exception {
        // When
        mockMvc.perform(post("/api/attributionCadeaux"))
                .andExpect(status().isOk());
    }
}