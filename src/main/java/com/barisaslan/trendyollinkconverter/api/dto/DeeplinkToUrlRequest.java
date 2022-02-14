package com.barisaslan.trendyollinkconverter.api.dto;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeeplinkToUrlRequest {

    @NotNull
    private String deeplink;

    public Deeplink toModel() {
        return new Deeplink(deeplink);
    }

}
