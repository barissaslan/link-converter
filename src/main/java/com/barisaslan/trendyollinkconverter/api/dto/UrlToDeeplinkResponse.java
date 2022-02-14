package com.barisaslan.trendyollinkconverter.api.dto;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlToDeeplinkResponse {

    private String deeplink;

    public static UrlToDeeplinkResponse fromModel(Deeplink deeplink) {
        return UrlToDeeplinkResponse.builder()
                .deeplink(deeplink.getValue())
                .build();
    }

}
