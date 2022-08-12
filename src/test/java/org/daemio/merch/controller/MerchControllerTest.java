package org.daemio.merch.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.daemio.merch.domain.Merch;
import org.daemio.merch.error.MerchNotFoundException;
import org.daemio.merch.model.MerchPage;
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
        var merch = new Merch();
        var expectedResponse = new MerchPage();
        expectedResponse.setMerch(Arrays.asList(merch));

        when(merchService.getMerchPage(any())).thenReturn(expectedResponse);

        mvc.perform(get("/merch"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.merch").isArray())
            .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }

    @DisplayName("given some page number when calling for a list of merch then the " +
        "service should return succesfully and the response should indicate the page " +
        "and have a list of merch")
    @Test
    public void givenPageNo_whenGetMerch_thenReturnSuccessfulList() throws Exception {
        var page = 2;
        var merch = new Merch();
        var expectedResponse = new MerchPage();
        expectedResponse.setMerch(Arrays.asList(merch));
        expectedResponse.setPage(page);
        expectedResponse.setSize(25);
        expectedResponse.setTotalPages(2);

        when(merchService.getMerchPage(any())).thenReturn(expectedResponse);

        mvc.perform(get("/merch").queryParam("page", Integer.toString(page)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.merch").isArray())
            .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }

    @DisplayName("given a merch id and the merch item exists, when calling for merch " +
        "by this id then the service should return successfully and the response should be " +
        "the specified merch item")
    @Test
    public void whenGetMerchItem_thenReturnSuccessfulItem() throws Exception {
        var merchId = 7;
        var merch = new Merch();
        merch.setId(merchId);

        when(merchService.getMerch(merchId)).thenReturn(merch);

        mvc.perform(get("/merch/{merchId}", merchId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(merchId));
    }

    @DisplayName("given a merch id where the item does not exist, whne calling for merch " +
        "by this id then the service sould return a 404 Not Found")
    @Test
    public void givenMerchNotThere_whenGetMerchItem_thenGetNotFoundResponse() throws Exception {
        var merchId = 7;

        when(merchService.getMerch(merchId)).thenThrow(MerchNotFoundException.class);

        mvc.perform(get("/merch/{merchId}", merchId))
            .andExpect(status().isNotFound());
    }
}
