package com.barisaslan.trendyollinkconverter.domain.parser;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;

import java.net.URL;

public interface UrlParserStrategy {

    LinkDetail parseUrl(URL url);

}
