package com.barisaslan.trendyollinkconverter.api.dto;

import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlToDeeplinkRequest {

    @NotNull
    private String url;

    public WebUrl toModel() {
        return new WebUrl(url);
    }

}
