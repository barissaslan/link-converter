package com.barisaslan.trendyollinkconverter.domain.model;

import lombok.Data;

@Data
public class LinkDetail {

    private PageType pageType;
    private String contentId;
    private String merchantId;
    private String bouqiqueId;
    private String searchValue;

}
