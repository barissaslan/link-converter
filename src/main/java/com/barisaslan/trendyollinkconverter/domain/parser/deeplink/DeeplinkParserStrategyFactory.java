package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.util.HashMap;
import java.util.Map;

public class DeeplinkParserStrategyFactory {

    private final Map<PageType, DeeplinkParserStrategy> conditions = new HashMap<>();

    public DeeplinkParserStrategyFactory() {
        conditions.put(PageType.OTHER_PAGE, new DefaultDeeplinkParser());
        conditions.put(PageType.SEARCH_PAGE, new SearchDeeplinkParser());
        conditions.put(PageType.PRODUCT_DETAIL_PAGE, new ProductDetailDeeplinkParser());
    }

    public DeeplinkParserStrategy getStrategy(PageType pageType) {
        return conditions.containsKey(pageType) ?
                conditions.get(pageType)
                : new DefaultDeeplinkParser();
    }

}
