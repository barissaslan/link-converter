package com.barisaslan.trendyollinkconverter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversion")
public class LinkConverterController {

    @GetMapping(value = "/deeplink/from_url")
    public ResponseEntity<String> convertUrlToDeeplink(@RequestParam String url) {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
