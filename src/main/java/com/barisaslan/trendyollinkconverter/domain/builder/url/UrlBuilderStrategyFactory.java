package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.util.HashMap;
import java.util.Map;

public class UrlBuilderStrategyFactory {

    private final Map<PageType, UrlBuilderStrategy> conditions = new HashMap<>();

    public UrlBuilderStrategyFactory() {
        conditions.put(PageType.OTHER_PAGE, new DefaultUrlBuilder());
        conditions.put(PageType.SEARCH_PAGE, new SearchUrlBuilder());
        conditions.put(PageType.PRODUCT_DETAIL_PAGE, new ProductDetailUrlBuilder());
    }

    public UrlBuilderStrategy getStrategy(PageType pageType) {
        return conditions.containsKey(pageType) ?
                conditions.get(pageType)
                : new DefaultUrlBuilder();
    }

}
