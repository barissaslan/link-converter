package com.barisaslan.trendyollinkconverter.api.controller;

import com.barisaslan.trendyollinkconverter.api.dto.DeeplinkToUrlRequest;
import com.barisaslan.trendyollinkconverter.api.dto.DeeplinkToUrlResponse;
import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkRequest;
import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkResponse;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import com.barisaslan.trendyollinkconverter.domain.service.DeeplinkConverterService;
import com.barisaslan.trendyollinkconverter.domain.service.UrlConverterService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.barisaslan.trendyollinkconverter.common.util.Utils.objectToJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LinkConverterControllerTest {

    @InjectMocks
    private LinkConverterController linkConverterController;

    @Mock
    private UrlConverterService urlConverterService;

    @Mock
    private DeeplinkConverterService deeplinkConverterService;

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

        MvcResult result = mvc.perform(post("/api/conversion/deeplink/from-url")
                        .content(objectToJsonString(UrlToDeeplinkRequest.builder().url("https://www.trendyol.com").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final UrlToDeeplinkResponse response = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertEquals("ty://?Page=Home", response.getDeeplink());
    }

    @Test
    void convertDeeplinkToUrlShouldReturnSuccess() throws Exception {
        when(deeplinkConverterService.convertDeeplinkToUrl(any(Deeplink.class)))
                .thenReturn(new WebUrl("https://www.trendyol.com/sr?q=elbise"));

        MvcResult result = mvc.perform(post("/api/conversion/url/from-deeplink")
                        .content(objectToJsonString(DeeplinkToUrlRequest.builder().deeplink("ty://?Page=Search&Query=elbise").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final DeeplinkToUrlResponse response = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertEquals("https://www.trendyol.com/sr?q=elbise", response.getUrl());
    }

}