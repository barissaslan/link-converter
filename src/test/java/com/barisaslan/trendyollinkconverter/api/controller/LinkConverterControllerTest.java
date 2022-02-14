package com.barisaslan.trendyollinkconverter.api.controller;

import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkResponse;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import com.barisaslan.trendyollinkconverter.domain.service.UrlConverterService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LinkConverterControllerTest {

    @InjectMocks
    private LinkConverterController linkConverterController;

    @Mock
    private UrlConverterService urlConverterService;

    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(linkConverterController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void convertUrlToDeeplinkShouldReturnSuccess() throws Exception {
        when(urlConverterService.convertUrlToDeeplink(any(WebUrl.class)))
                .thenReturn(new Deeplink("ty://?Page=Home"));

        MvcResult result = mvc.perform(get("/api/conversion/deeplink/from_url?url=https://www.trendyol.com"))
                .andExpect(status().isOk()).andReturn();

        final UrlToDeeplinkResponse response = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertEquals("ty://?Page=Home", response.getDeeplink());
    }

}