package com.barisaslan.trendyollinkconverter.domain.builder.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.DEEPLINK_SEARCH_PAGE_PLACEHOLDER;

public class SearchDeeplinkBuilder implements DeeplinkBuilderStrategy {

    @Override
    public Deeplink buildDeeplink(LinkDetail linkDetail) {
        return new Deeplink(String.format(DEEPLINK_SEARCH_PAGE_PLACEHOLDER, linkDetail.getSearchValue()));
    }

}
