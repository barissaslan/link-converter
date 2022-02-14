package com.barisaslan.trendyollinkconverter.api.dto;

import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeeplinkToUrlResponse {

    private String url;

    public static DeeplinkToUrlResponse fromModel(WebUrl webUrl) {
        return DeeplinkToUrlResponse.builder()
                .url(webUrl.getValue())
                .build();
    }

}
