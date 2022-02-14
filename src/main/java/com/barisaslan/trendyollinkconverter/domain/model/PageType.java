package com.barisaslan.trendyollinkconverter.domain.model;

public enum PageType {

    OTHER_PAGE(""),
    PRODUCT_DETAIL_PAGE("Product"),
    SEARCH_PAGE("Search");

    private final String deeplinkKey;

    PageType(String deeplinkKey) {
        this.deeplinkKey = deeplinkKey;
    }

    public String getDeeplinkKey() {
        return deeplinkKey;
    }

}
