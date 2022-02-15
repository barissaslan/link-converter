package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.URL_SEARCH_PAGE_PLACEHOLDER;

public class SearchUrlBuilder implements UrlBuilderStrategy {

    @Override
    public WebUrl buildUrl(LinkDetail linkDetail) {
        return new WebUrl(String.format(URL_SEARCH_PAGE_PLACEHOLDER, linkDetail.getSearchValue()));
    }

}
