package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidUrlException;
import com.barisaslan.trendyollinkconverter.domain.builder.deeplink.DeeplinkBuilderStrategy;
import com.barisaslan.trendyollinkconverter.domain.builder.deeplink.DeeplinkBuilderStrategyFactory;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import com.barisaslan.trendyollinkconverter.domain.parser.url.UrlParserStrategy;
import com.barisaslan.trendyollinkconverter.domain.parser.url.UrlParserStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
import static com.barisaslan.trendyollinkconverter.common.util.Utils.objectToJsonString;

@Slf4j
@Service
public class UrlConverterServiceImpl implements UrlConverterService {

    private URL url;

    @Override
    public Deeplink convertUrlToDeeplink(WebUrl webUrl) throws InvalidUrlException {
        if (isNotValidTrendyolUrl(webUrl.getValue())) {
            log.error("Invalid Url Exception. Url: " + webUrl.getValue());
            throw new InvalidUrlException();
        }

        LinkDetail linkDetail = parseWebUrl();
        log.debug("Parsed web url: " + objectToJsonString(linkDetail));

        return buildDeeplink(linkDetail);
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
        String path = url.getPath();
        String pathWithQuery = url.getFile();

        PageType pageType = PageType.OTHER_PAGE;

        if (isProductDetailPage(path)) {
            pageType = PageType.PRODUCT_DETAIL_PAGE;
        } else if (isSearchPage(pathWithQuery)) {
            pageType = PageType.SEARCH_PAGE;
        }

        UrlParserStrategyFactory urlParserStrategyFactory = new UrlParserStrategyFactory();
        UrlParserStrategy urlParserStrategy = urlParserStrategyFactory.getStrategy(pageType);

        return urlParserStrategy.parseUrl(url);
    }

    private boolean isProductDetailPage(String path) {
        Pattern pattern = Pattern.compile(URL_PRODUCT_DETAIL_PAGE_VALIDATOR_REGEX);
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
    }

    private boolean isSearchPage(String path) {
        Pattern pattern = Pattern.compile(URL_SEARCH_PAGE_VALIDATOR_REGEX);
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
    }

    private Deeplink buildDeeplink(LinkDetail linkDetail) {
        DeeplinkBuilderStrategyFactory deeplinkBuilderStrategyFactory = new DeeplinkBuilderStrategyFactory();
        DeeplinkBuilderStrategy deeplinkBuilderStrategy = deeplinkBuilderStrategyFactory.getStrategy(linkDetail.getPageType());

        return deeplinkBuilderStrategy.buildDeeplink(linkDetail);
    }

}
