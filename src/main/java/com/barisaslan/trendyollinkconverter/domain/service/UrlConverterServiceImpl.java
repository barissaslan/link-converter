package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidUrlException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
import static com.barisaslan.trendyollinkconverter.common.util.Utils.hasValue;

public class UrlConverterServiceImpl implements UrlConverterService {

    private URL url;

    @Override
    public Deeplink convertUrlToDeeplink(WebUrl webUrl) throws InvalidUrlException {
        if (isNotValidTrendyolUrl(webUrl.getValue())) {
            throw new InvalidUrlException();
        }

        LinkDetail linkDetail = parseWebUrl();

        return generateDeeplink(linkDetail);
    }

    private boolean isNotValidTrendyolUrl(String value) {
        return !isValidTrendyolUrl(value);
    }

    private boolean isValidTrendyolUrl(String value) {
        try {
            url = new URL(value);

            if (isNotTrendyolHostName(url.getHost())) {
                return false;
            }
        } catch (MalformedURLException e) {
            return false;
        }

        return true;
    }

    private boolean isNotTrendyolHostName(String hostName) {
        return !isTrendyolHostName(hostName);
    }

    private boolean isTrendyolHostName(String hostName) {
        Pattern pattern = Pattern.compile(TRENDYOL_HOST_NAME_VALIDATOR_REGEX);
        Matcher matcher = pattern.matcher(hostName);
        return matcher.matches();
    }

    private LinkDetail parseWebUrl() {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setPageType(PageType.OTHER_PAGE);

        String path = url.getPath();
        String pathWithQuery = url.getFile();

        if (isProductDetailPage(path)) {
            String contentId = getContentId(path);
            linkDetail.setContentId(contentId);
            linkDetail.setPageType(PageType.PRODUCT_DETAIL_PAGE);

            if (hasValue(url.getQuery())) {
                populateWithSearchQueryParameters(linkDetail);
            }
        } else if (isSearchPage(pathWithQuery)) {
            String searchQuery = getSearchQuery(pathWithQuery);
            linkDetail.setSearchQuery(searchQuery);
            linkDetail.setPageType(PageType.SEARCH_PAGE);
        }

        return linkDetail;
    }

    private boolean isProductDetailPage(String path) {
        Pattern pattern = Pattern.compile(URL_PRODUCT_DETAIL_PAGE_VALIDATOR_REGEX);
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
    }

    private String getContentId(String path) {
        return path.replaceAll(URL_PRODUCT_DETAIL_PAGE_CONTENT_ID_FINDER_REGEX, "");
    }

    private void populateWithSearchQueryParameters(LinkDetail linkDetail) {
        Map<String, String> queryMap = parseSearchQuery();

        if (hasValue(queryMap.get(URL_MERCHANT_ID_KEY))) {
            linkDetail.setMerchantId(queryMap.get(URL_MERCHANT_ID_KEY));
        }

        if (hasValue(queryMap.get(URL_BOUTIQUE_ID_KEY))) {
            linkDetail.setBouqiqueId(queryMap.get(URL_BOUTIQUE_ID_KEY));
        }
    }

    private HashMap<String, String> parseSearchQuery() {
        HashMap<String, String> queryMap = new HashMap<>();

        Arrays.stream(url.getQuery().split(AMPERSAND_CHARACTER))
                .forEach(item -> queryMap.put(item.split(EQUAL_CHARACTER)[0], item.split(EQUAL_CHARACTER)[1]));

        return queryMap;
    }

    private boolean isSearchPage(String path) {
        Pattern pattern = Pattern.compile(URL_SEARCH_PAGE_VALIDATOR_REGEX);
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
    }

    private String getSearchQuery(String path) {
        return path.replaceAll(URL_SEARCH_QUERY_FINDER_REGEX, "");
    }

    private Deeplink generateDeeplink(LinkDetail linkDetail) {
        StringBuilder deeplinkBuilder = new StringBuilder();

        switch (linkDetail.getPageType()) {
            case PRODUCT_DETAIL_PAGE:
                generateProductDetailDeeplink(linkDetail, deeplinkBuilder);
                break;
            case SEARCH_PAGE:
                deeplinkBuilder.append(String.format(DEEPLINK_SEARCH_PAGE_PLACEHOLDER, linkDetail.getSearchQuery()));
                break;
            case OTHER_PAGE:
            default:
                deeplinkBuilder.append(DEEPLINK_HOME_PAGE);
                break;
        }

        return new Deeplink(deeplinkBuilder.toString());
    }

    private void generateProductDetailDeeplink(LinkDetail linkDetail, StringBuilder deeplinkBuilder) {
        deeplinkBuilder.append(String.format(DEEPLINK_PRODUCT_DETAIL_PAGE_PLACEHOLDER, linkDetail.getContentId()));

        if (hasValue(linkDetail.getBouqiqueId())) {
            deeplinkBuilder.append(AMPERSAND_CHARACTER)
                    .append(DEEPLINK_BOUTIQUE_ID_KEY)
                    .append(EQUAL_CHARACTER)
                    .append(linkDetail.getBouqiqueId());
        }

        if (hasValue(linkDetail.getMerchantId())) {
            deeplinkBuilder.append(AMPERSAND_CHARACTER)
                    .append(DEEPLINK_MERCHANT_ID_KEY)
                    .append(EQUAL_CHARACTER)
                    .append(linkDetail.getMerchantId());
        }
    }

}
