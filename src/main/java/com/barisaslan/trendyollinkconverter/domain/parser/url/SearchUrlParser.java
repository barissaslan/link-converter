package com.barisaslan.trendyollinkconverter.domain.parser.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.net.URL;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.URL_SEARCH_QUERY_FINDER_REGEX;

public class SearchUrlParser implements UrlParserStrategy {

    @Override
    public LinkDetail parseUrl(URL url) {
        LinkDetail linkDetail = new LinkDetail();

        String pathWithQuery = url.getFile();
        String searchValue = getSearchQuery(pathWithQuery);

        linkDetail.setSearchValue(searchValue);
        linkDetail.setPageType(PageType.SEARCH_PAGE);

        return linkDetail;
    }

    private String getSearchQuery(String path) {
        return path.replaceAll(URL_SEARCH_QUERY_FINDER_REGEX, "");
    }

}
