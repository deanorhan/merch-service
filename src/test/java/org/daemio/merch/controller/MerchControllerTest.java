package org.daemio.merch.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.daemio.merch.dto.MerchListResponse;
import org.daemio.merch.model.Merch;
import org.daemio.merch.service.MerchService;

@WebMvcTest(MerchController.class)
@DisplayName("Merch controller tests")
public class MerchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MerchService merchService;

    private final ObjectMapper mapper = new ObjectMapper();

    @DisplayName("when calling for a list of merch then the service should return " +
        "succussfully and the response should be an array of merch")
    @Test
    public void whenGetMerch_thenReturnSuccessfulList() throws Exception {
        Merch merch = new Merch();
        MerchListResponse expectedResponse = new MerchListResponse();
        expectedResponse.setMerch(Arrays.asList(merch));

        when(merchService.getMerchList()).thenReturn(Arrays.asList(merch));

        mvc.perform(get("/merch").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.merch").isArray())
            .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }
}
