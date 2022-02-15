package com.barisaslan.trendyollinkconverter.api.controller;

import com.barisaslan.trendyollinkconverter.api.dto.DeeplinkToUrlRequest;
import com.barisaslan.trendyollinkconverter.api.dto.DeeplinkToUrlResponse;
import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkRequest;
import com.barisaslan.trendyollinkconverter.api.dto.UrlToDeeplinkResponse;
import com.barisaslan.trendyollinkconverter.common.exception.InvalidDeeplinkException;
import com.barisaslan.trendyollinkconverter.common.exception.InvalidUrlException;
import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import com.barisaslan.trendyollinkconverter.domain.service.DeeplinkConverterService;
import com.barisaslan.trendyollinkconverter.domain.service.UrlConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conversion")
public class LinkConverterController {

    private final UrlConverterService urlConverterService;
    private final DeeplinkConverterService deeplinkConverterService;

    @PostMapping(value = "/deeplink/from-url")
    public ResponseEntity<UrlToDeeplinkResponse> convertUrlToDeeplink(@RequestBody @Valid UrlToDeeplinkRequest request) throws InvalidUrlException {
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(request.toModel());
        return new ResponseEntity<>(UrlToDeeplinkResponse.fromModel(deeplink), HttpStatus.OK);
    }

    @PostMapping(value = "/url/from-deeplink")
    public ResponseEntity<DeeplinkToUrlResponse> convertDeeplinkToUrl(@RequestBody @Valid DeeplinkToUrlRequest request) throws InvalidDeeplinkException, UrlBuildException {
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(request.toModel());
        return new ResponseEntity<>(DeeplinkToUrlResponse.fromModel(webUrl), HttpStatus.OK);
    }

}
