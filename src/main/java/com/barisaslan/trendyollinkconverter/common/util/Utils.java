package com.barisaslan.trendyollinkconverter.common.util;

public class Utils {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean hasValue(String value) {
        return !isNullOrEmpty(value);
    }

}
