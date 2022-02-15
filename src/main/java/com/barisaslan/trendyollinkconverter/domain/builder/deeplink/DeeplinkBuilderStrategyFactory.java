package com.barisaslan.trendyollinkconverter.domain.builder.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.util.HashMap;
import java.util.Map;

public class DeeplinkBuilderStrategyFactory {

    private final Map<PageType, DeeplinkBuilderStrategy> conditions = new HashMap<>();

    public DeeplinkBuilderStrategyFactory() {
        conditions.put(PageType.OTHER_PAGE, new DefaultDeeplinkBuilder());
        conditions.put(PageType.SEARCH_PAGE, new SearchDeeplinkBuilder());
        conditions.put(PageType.PRODUCT_DETAIL_PAGE, new ProductDetailDeeplinkBuilder());
    }

    public DeeplinkBuilderStrategy getStrategy(PageType pageType) {
        return conditions.containsKey(pageType) ?
                conditions.get(pageType)
                : new DefaultDeeplinkBuilder();
    }

}
