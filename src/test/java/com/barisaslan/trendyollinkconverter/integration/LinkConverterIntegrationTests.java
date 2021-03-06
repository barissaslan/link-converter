package com.barisaslan.trendyollinkconverter.integration;

import com.barisaslan.trendyollinkconverter.api.controller.LinkConverterController;
import com.barisaslan.trendyollinkconverter.api.dto.DeeplinkToUrlRequest;
import com.barisaslan.trendyollinkconverter.api.dto.DeeplinkToUrlResponse;
import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkRequest;
import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkResponse;
import com.barisaslan.trendyollinkconverter.common.filter.CallLogFilter;
import com.barisaslan.trendyollinkconverter.dao.entity.ControllerCallLog;
import com.barisaslan.trendyollinkconverter.dao.repository.ControllerCallLogRepository;
import com.barisaslan.trendyollinkconverter.domain.service.ControllerCallLogService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.barisaslan.trendyollinkconverter.common.util.Utils.objectToJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class LinkConverterIntegrationTests {

    private MockMvc mvc;

    @Autowired
    private LinkConverterController linkConverterController;

    @Autowired
    private ControllerCallLogService controllerCallLogService;

    @Autowired
    private ControllerCallLogRepository controllerCallLogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.mvc = MockMvcBuilders
                .standaloneSetup(linkConverterController)
                .addFilter(new CallLogFilter(controllerCallLogService))
                .build();
    }

    @AfterEach
    void cleanUpEach() {
        controllerCallLogRepository.deleteAll();
    }

    @Test
    void convertUrlToDeeplinkShouldReturnDeeplink() throws Exception {
        MvcResult result = mvc.perform(post("/api/conversion/deeplink/from-url")
                        .content(objectToJsonString(UrlToDeeplinkRequest.builder().url("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final UrlToDeeplinkResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals("ty://?Page=Search&Query=%C3%BCt%C3%BC", response.getDeeplink());
    }

    @Test
    void convertUrlToDeeplinkShouldSaveCallLogToDatabase() throws Exception {
        mvc.perform(post("/api/conversion/deeplink/from-url")
                        .content(objectToJsonString(UrlToDeeplinkRequest.builder().url("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<ControllerCallLog> callLog = controllerCallLogRepository.findAll();

        assertEquals(callLog.size(), 1);
    }

    @Test
    void convertUrlToDeeplinkShouldReturn406() throws Exception {
        MvcResult result = mvc.perform(post("/api/conversion/deeplink/from-url")
                        .content(objectToJsonString(UrlToDeeplinkRequest.builder().url("https://www.test.com/").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();

        assertEquals(result.getResponse().getStatus(), 406);
    }

    @Test
    void convertDeeplinkToUrlShouldReturnUrl() throws Exception {
        MvcResult result = mvc.perform(post("/api/conversion/url/from-deeplink")
                        .content(objectToJsonString(DeeplinkToUrlRequest.builder().deeplink("ty://?Page=Search&Query=%C3%BCt%C3%BC").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final DeeplinkToUrlResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC", response.getUrl());
    }

    @Test
    void convertDeeplinkToUrlShouldSaveCallLogToDatabase() throws Exception {
        mvc.perform(post("/api/conversion/url/from-deeplink")
                        .content(objectToJsonString(DeeplinkToUrlRequest.builder().deeplink("ty://?Page=Search&Query=%C3%BCt%C3%BC").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<ControllerCallLog> callLog = controllerCallLogRepository.findAll();

        assertEquals(callLog.size(), 1);
    }

    @Test
    void convertDeeplinkToUrlShouldReturn406() throws Exception {
        MvcResult result = mvc.perform(post("/api/conversion/url/from-deeplink")
                        .content(objectToJsonString(DeeplinkToUrlRequest.builder().deeplink("twitter://?Page=Search&Query=%C3%BCt%C3%BC").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();

        assertEquals(result.getResponse().getStatus(), 406);
    }

}
