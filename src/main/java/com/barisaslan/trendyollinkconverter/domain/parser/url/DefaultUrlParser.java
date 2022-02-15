package com.barisaslan.trendyollinkconverter.domain.parser.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.net.URL;

public class DefaultUrlParser implements UrlParserStrategy {

    @Override
    public LinkDetail parseUrl(URL url) {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setPageType(PageType.OTHER_PAGE);
        return linkDetail;
    }

}
