package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidUrlException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;

public interface UrlConverterService {

    Deeplink convertUrlToDeeplink(WebUrl webUrl) throws InvalidUrlException;

}
