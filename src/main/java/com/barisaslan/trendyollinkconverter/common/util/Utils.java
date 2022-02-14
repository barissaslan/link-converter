package com.barisaslan.trendyollinkconverter.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean hasValue(String value) {
        return !isNullOrEmpty(value);
    }

    public static String objectToJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Error at objectToJsonString: " + e.getMessage());
            throw new RuntimeException();
        }
    }

}
