package com.barisaslan.trendyollinkconverter;

import com.barisaslan.trendyollinkconverter.domain.model.ControllerCallLogDto;

public class TestHelper {

    public static ControllerCallLogDto getFakeControllerCallLogDto() {
        return ControllerCallLogDto.builder()
                .startTime(10L)
                .endTime(20L)
                .statusCode(200)
                .build();
    }

}
