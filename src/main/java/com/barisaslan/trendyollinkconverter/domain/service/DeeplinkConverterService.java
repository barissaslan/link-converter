package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidDeeplinkException;
import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;

public interface DeeplinkConverterService {

    WebUrl convertDeeplinkToUrl(Deeplink deeplink) throws InvalidDeeplinkException, UrlBuildException;

}
