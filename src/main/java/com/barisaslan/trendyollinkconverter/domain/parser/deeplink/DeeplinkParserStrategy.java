package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;

import java.util.HashMap;

public interface DeeplinkParserStrategy {

    LinkDetail parseDeeplink(HashMap<String, String> elementMap);

}
