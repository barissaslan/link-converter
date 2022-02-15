package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;

import java.util.HashMap;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;

public class ProductDetailDeeplinkParser implements DeeplinkParserStrategy {

    @Override
    public LinkDetail parseDeelink(HashMap<String, String> elementMap) {
        LinkDetail linkDetail = new LinkDetail();

        String contentId = elementMap.get(DEEPLINK_CONTENT_ID_KEY);
        linkDetail.setContentId(contentId);
        linkDetail.setPageType(PageType.PRODUCT_DETAIL_PAGE);

        populateWithSearchQueryParameters(linkDetail, elementMap);

        return linkDetail;
    }

    private void populateWithSearchQueryParameters(LinkDetail linkDetail, HashMap<String, String> elementMap) {
        if (elementMap.containsKey(DEEPLINK_MERCHANT_ID_KEY)) {
            linkDetail.setMerchantId(elementMap.get(DEEPLINK_MERCHANT_ID_KEY));
        }

        if (elementMap.containsKey(DEEPLINK_BOUTIQUE_ID_KEY)) {
            linkDetail.setBouqiqueId(elementMap.get(DEEPLINK_BOUTIQUE_ID_KEY));
        }
    }

}
