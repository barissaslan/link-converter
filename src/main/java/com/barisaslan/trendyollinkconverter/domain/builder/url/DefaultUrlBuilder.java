package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.URL_HOME_PAGE;

public class DefaultUrlBuilder implements UrlBuilderStrategy {

    @Override
    public WebUrl buildDeeplink(LinkDetail linkDetail) {
        return new WebUrl(URL_HOME_PAGE);
    }

}
