package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.util.HashMap;

public class DefaultDeeplinkParser implements DeeplinkParserStrategy {

    @Override
    public LinkDetail parseDeelink(HashMap<String, String> elementMap) {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setPageType(PageType.OTHER_PAGE);
        return linkDetail;
    }

}
