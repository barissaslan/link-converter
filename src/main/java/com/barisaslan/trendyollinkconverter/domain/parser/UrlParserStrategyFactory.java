package com.barisaslan.trendyollinkconverter.domain.parser;

import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.util.HashMap;
import java.util.Map;

public class UrlParserStrategyFactory {

    private final Map<PageType, UrlParserStrategy> conditions = new HashMap<>();

    public UrlParserStrategyFactory() {
        conditions.put(PageType.OTHER_PAGE, new DefaultUrlParser());
        conditions.put(PageType.SEARCH_PAGE, new SearchUrlParser());
        conditions.put(PageType.PRODUCT_DETAIL_PAGE, new ProductDetailUrlParser());
    }

    public UrlParserStrategy getStrategy(PageType pageType) {
        return conditions.containsKey(pageType) ?
                conditions.get(pageType)
                : new DefaultUrlParser();
    }

}
