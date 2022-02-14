package com.barisaslan.trendyollinkconverter.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ControllerCallLogDto {

    private String requestBody;
    private String responseBody;
    private String apiPath;
    private Integer statusCode;
    private Long startTime;
    private Long endTime;

}
