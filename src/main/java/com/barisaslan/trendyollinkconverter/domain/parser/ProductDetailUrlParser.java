package com.barisaslan.trendyollinkconverter.domain.parser;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
import static com.barisaslan.trendyollinkconverter.common.util.Utils.hasValue;

public class ProductDetailUrlParser implements UrlParserStrategy {

    @Override
    public LinkDetail parseUrl(URL url) {
        LinkDetail linkDetail = new LinkDetail();

        String contentId = getContentId(url.getPath());
        linkDetail.setContentId(contentId);
        linkDetail.setPageType(PageType.PRODUCT_DETAIL_PAGE);

        if (hasValue(url.getQuery())) {
            populateWithSearchQueryParameters(url.getQuery(), linkDetail);
        }

        return linkDetail;
    }

    private String getContentId(String path) {
        return path.replaceAll(URL_PRODUCT_DETAIL_PAGE_CONTENT_ID_FINDER_REGEX, "");
    }

    private void populateWithSearchQueryParameters(String query, LinkDetail linkDetail) {
        Map<String, String> queryMap = parseSearchQuery(query);

        if (hasValue(queryMap.get(URL_MERCHANT_ID_KEY))) {
            linkDetail.setMerchantId(queryMap.get(URL_MERCHANT_ID_KEY));
        }

        if (hasValue(queryMap.get(URL_BOUTIQUE_ID_KEY))) {
            linkDetail.setBouqiqueId(queryMap.get(URL_BOUTIQUE_ID_KEY));
        }
    }

    private HashMap<String, String> parseSearchQuery(String query) {
        HashMap<String, String> queryMap = new HashMap<>();

        Arrays.stream(query.split(AMPERSAND_CHARACTER))
                .forEach(item -> queryMap.put(item.split(EQUAL_CHARACTER)[0], item.split(EQUAL_CHARACTER)[1]));

        return queryMap;
    }

}
