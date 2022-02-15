package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;

public interface UrlBuilderStrategy {

    WebUrl buildDeeplink(LinkDetail linkDetail) throws UrlBuildException;

}
