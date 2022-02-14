package com.barisaslan.trendyollinkconverter.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid URL!")
public class InvalidUrlException extends Exception {
    public InvalidUrlException() {
        super("Provide valid Trendyol URL.");
    }
}
