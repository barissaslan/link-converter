package com.barisaslan.trendyollinkconverter.api.controller;

import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkRequest;
import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkResponse;
import com.barisaslan.trendyollinkconverter.common.exception.InvalidUrlException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.service.UrlConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conversion")
public class LinkConverterController {

    private final UrlConverterService urlConverterService;

    @GetMapping(value = "/deeplink/from_url")
    public ResponseEntity<UrlToDeeplinkResponse> convertUrlToDeeplink(@Valid UrlToDeeplinkRequest request) throws InvalidUrlException {
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(request.toModel());
        return new ResponseEntity<>(UrlToDeeplinkResponse.fromModel(deeplink), HttpStatus.OK);
    }

}
