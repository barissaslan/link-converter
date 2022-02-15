package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
import static com.barisaslan.trendyollinkconverter.common.util.Utils.hasValue;

@Slf4j
public class ProductDetailUrlBuilder implements UrlBuilderStrategy {

    @Override
    public WebUrl buildUrl(LinkDetail linkDetail) throws UrlBuildException {
        StringBuilder deeplinkBuilder = new StringBuilder();

        String path = String.format(URL_PRODUCT_DETAIL_PAGE_PATH_PLACEHOLDER, linkDetail.getContentId());
        String query = buildQueryParameters(linkDetail);

        try {
            URI uri = new URI(URL_PROTOCOL, null, URL_HOST, URL_PORT, path, query, null);
            deeplinkBuilder.append(uri.toASCIIString().replaceFirst(":80", ""));
        } catch (URISyntaxException e) {
            log.error("Error ocurred when building URI: " + e.getMessage());
            throw new UrlBuildException();
        }

        return new WebUrl(deeplinkBuilder.toString());
    }

    private String buildQueryParameters(LinkDetail linkDetail) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (hasValue(linkDetail.getBouqiqueId())) {
            map.put(URL_BOUTIQUE_ID_KEY, linkDetail.getBouqiqueId());
        }

        if (hasValue(linkDetail.getMerchantId())) {
            map.put(URL_MERCHANT_ID_KEY, linkDetail.getMerchantId());
        }

        StringBuilder queryBuilder = new StringBuilder();

        boolean isQueryInitilized = false;

        for (String key : map.keySet()) {
            if (isQueryInitilized) {
                queryBuilder.append(AMPERSAND_CHARACTER);
            } else {
                isQueryInitilized = true;
            }

            queryBuilder.append(key).append(EQUAL_CHARACTER).append(map.get(key));
        }

        return queryBuilder.toString().length() > 0 ? queryBuilder.toString() : null;
    }

}
