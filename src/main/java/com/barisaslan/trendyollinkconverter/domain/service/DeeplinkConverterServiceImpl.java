package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidDeeplinkException;
import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.builder.url.UrlBuilderStrategy;
import com.barisaslan.trendyollinkconverter.domain.builder.url.UrlBuilderStrategyFactory;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import com.barisaslan.trendyollinkconverter.domain.parser.deeplink.DeeplinkParserStrategy;
import com.barisaslan.trendyollinkconverter.domain.parser.deeplink.DeeplinkParserStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
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
        PageType pageType = PageType.OTHER_PAGE;
        HashMap<String, String> elementMap = parseDeeplinkElements();

        if (isProductDetailPage(elementMap)) {
            pageType = PageType.PRODUCT_DETAIL_PAGE;
        } else if (isSearchPage(elementMap)) {
            pageType = PageType.SEARCH_PAGE;
        }

        DeeplinkParserStrategyFactory deeplinkParserStrategyFactory = new DeeplinkParserStrategyFactory();
        DeeplinkParserStrategy deeplinkParserStrategy = deeplinkParserStrategyFactory.getStrategy(pageType);

        return deeplinkParserStrategy.parseDeelink(elementMap);
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


    private boolean isSearchPage(HashMap<String, String> elementMap) {
        return PageType.SEARCH_PAGE.getDeeplinkKey().equals(elementMap.get(DEEPLINK_PAGE_KEY))
                && elementMap.containsKey(DEEPLINK_QUERY_KEY);
    }

    private WebUrl buildWebUrl(LinkDetail linkDetail) throws UrlBuildException {
        UrlBuilderStrategyFactory urlBuilderStrategyFactory = new UrlBuilderStrategyFactory();
        UrlBuilderStrategy urlBuilderStrategy = urlBuilderStrategyFactory.getStrategy(linkDetail.getPageType());

        return urlBuilderStrategy.buildDeeplink(linkDetail);
    }

}
