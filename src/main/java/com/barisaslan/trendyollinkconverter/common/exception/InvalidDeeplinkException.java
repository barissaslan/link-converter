package com.barisaslan.trendyollinkconverter.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid Deeplink!")
public class InvalidDeeplinkException extends Exception {
    public InvalidDeeplinkException() {
        super("Provide valid Trendyol Deeplink.");
    }
}
