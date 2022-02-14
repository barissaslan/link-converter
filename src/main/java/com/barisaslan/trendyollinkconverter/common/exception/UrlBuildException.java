package com.barisaslan.trendyollinkconverter.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "URL Build Exception!")
public class UrlBuildException extends Exception {
    public UrlBuildException() {
        super("Error ocurred when building URL.");
    }
}
