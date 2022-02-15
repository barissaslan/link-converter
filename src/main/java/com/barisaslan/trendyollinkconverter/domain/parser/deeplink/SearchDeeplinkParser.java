package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.util.HashMap;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.DEEPLINK_QUERY_KEY;

public class SearchDeeplinkParser implements DeeplinkParserStrategy {

    @Override
    public LinkDetail parseDeelink(HashMap<String, String> elementMap) {
        LinkDetail linkDetail = new LinkDetail();

        String searchQuery = elementMap.get(DEEPLINK_QUERY_KEY);
        linkDetail.setSearchValue(searchQuery);
        linkDetail.setPageType(PageType.SEARCH_PAGE);

        return linkDetail;
    }

}
