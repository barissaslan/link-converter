package com.barisaslan.trendyollinkconverter.domain.builder;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.DEEPLINK_HOME_PAGE;

public class DefaultDeeplinkBuilder implements DeeplinkBuilderStrategy {

    @Override
    public Deeplink buildDeeplink(LinkDetail linkDetail) {
        return new Deeplink(DEEPLINK_HOME_PAGE);
    }

}
