package com.barisaslan.trendyollinkconverter.domain.builder;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;

public interface DeeplinkBuilderStrategy {

    Deeplink buildDeeplink(LinkDetail linkDetail);

}
