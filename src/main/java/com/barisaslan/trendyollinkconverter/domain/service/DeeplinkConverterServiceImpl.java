package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidDeeplinkException;
import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
import static com.barisaslan.trendyollinkconverter.common.util.Utils.hasValue;
import static com.barisaslan.trendyollinkconverter.common.util.Utils.objectToJsonString;

@Slf4j
@Service
public class DeeplinkConverterServiceImpl implements DeeplinkConverterService {

    private String deeplinkValue;

    @Override
    public WebUrl convertDeeplinkToUrl(Deeplink deeplink) throws InvalidDeeplinkException, UrlBuildException {
        if (isNotValidTrendyolDeeplink(deeplink.getValue())) {
            log.error("Invalid Deeplink Exception. Value: " + deeplink.getValue());
            throw new InvalidDeeplinkException();
        }

        deeplinkValue = deeplink.getValue();

        LinkDetail linkDetail = parseDeeplink();
        log.debug("Parsed deeplink: " + objectToJsonString(linkDetail));

        return buildWebUrl(linkDetail);
    }

    private boolean isNotValidTrendyolDeeplink(String value) {
        return !isValidTrendyolDeeplink(value);
    }

    private boolean isValidTrendyolDeeplink(String value) {
        Pattern pattern = Pattern.compile(TRENDYOL_DEEPLINK_VALIDATOR_REGEX);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private LinkDetail parseDeeplink() {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setPageType(PageType.OTHER_PAGE);

        HashMap<String, String> elementMap = parseDeeplinkElements();

        if (isProductDetailPage(elementMap)) {
            String contentId = elementMap.get(DEEPLINK_CONTENT_ID_KEY);
            linkDetail.setContentId(contentId);
            linkDetail.setPageType(PageType.PRODUCT_DETAIL_PAGE);
            populateWithSearchQueryParameters(linkDetail, elementMap);
        } else if (isSearchPage(elementMap)) {
            String searchQuery = elementMap.get(DEEPLINK_QUERY_KEY);
            linkDetail.setSearchQuery(searchQuery);
            linkDetail.setPageType(PageType.SEARCH_PAGE);
        }

        return linkDetail;
    }

    private HashMap<String, String> parseDeeplinkElements() {
        HashMap<String, String> queryMap = new HashMap<>();
        String elements = deeplinkValue.split(QUESTION_MARK_REGEX)[1];

        Arrays.stream(elements.split(AMPERSAND_CHARACTER))
                .forEach(item -> queryMap.put(item.split(EQUAL_CHARACTER)[0], item.split(EQUAL_CHARACTER)[1]));

        return queryMap;
    }

    private boolean isProductDetailPage(HashMap<String, String> elementMap) {
        return PageType.PRODUCT_DETAIL_PAGE.getDeeplinkKey().equals(elementMap.get(DEEPLINK_PAGE_KEY))
                && elementMap.containsKey(DEEPLINK_CONTENT_ID_KEY);
    }

    private void populateWithSearchQueryParameters(LinkDetail linkDetail, HashMap<String, String> elementMap) {
        if (elementMap.containsKey(DEEPLINK_MERCHANT_ID_KEY)) {
            linkDetail.setMerchantId(elementMap.get(DEEPLINK_MERCHANT_ID_KEY));
        }

        if (elementMap.containsKey(DEEPLINK_BOUTIQUE_ID_KEY)) {
            linkDetail.setBouqiqueId(elementMap.get(DEEPLINK_BOUTIQUE_ID_KEY));
        }
    }

    private boolean isSearchPage(HashMap<String, String> elementMap) {
        return PageType.SEARCH_PAGE.getDeeplinkKey().equals(elementMap.get(DEEPLINK_PAGE_KEY))
                && elementMap.containsKey(DEEPLINK_QUERY_KEY);
    }

    private WebUrl buildWebUrl(LinkDetail linkDetail) throws UrlBuildException {
        StringBuilder urlBuilder = new StringBuilder();

        switch (linkDetail.getPageType()) {
            case PRODUCT_DETAIL_PAGE:
                buildProductDetailUrl(linkDetail, urlBuilder);
                break;
            case SEARCH_PAGE:
                urlBuilder.append(String.format(URL_SEARCH_PAGE_PLACEHOLDER, linkDetail.getSearchQuery()));
                break;
            case OTHER_PAGE:
            default:
                urlBuilder.append(URL_HOME_PAGE);
                break;
        }

        return new WebUrl(urlBuilder.toString());
    }

    private void buildProductDetailUrl(LinkDetail linkDetail, StringBuilder deeplinkBuilder) throws UrlBuildException {
        String path = String.format(URL_PRODUCT_DETAIL_PAGE_PATH_PLACEHOLDER, linkDetail.getContentId());
        String query = buildQueryParameters(linkDetail);

        try {
            URI uri = new URI(URL_PROTOCOL, null, URL_HOST, URL_PORT, path, query, null);
            deeplinkBuilder.append(uri.toASCIIString().replaceFirst(":80", ""));
        } catch (URISyntaxException e) {
            log.error("Error ocurred when building URI: " + e.getMessage());
            throw new UrlBuildException();
        }
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
