package com.barisaslan.trendyollinkconverter.domain.builder.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
import static com.barisaslan.trendyollinkconverter.common.util.Utils.hasValue;

public class ProductDetailDeeplinkBuilder implements DeeplinkBuilderStrategy {

    @Override
    public Deeplink buildDeeplink(LinkDetail linkDetail) {
        String productDetailBaseDeeplink = String.format(DEEPLINK_PRODUCT_DETAIL_PAGE_PLACEHOLDER, linkDetail.getContentId());
        StringBuilder deeplinkBuilder = new StringBuilder(productDetailBaseDeeplink);

        if (hasValue(linkDetail.getBouqiqueId())) {
            deeplinkBuilder.append(AMPERSAND_CHARACTER)
                    .append(DEEPLINK_BOUTIQUE_ID_KEY)
                    .append(EQUAL_CHARACTER)
                    .append(linkDetail.getBouqiqueId());
        }

        if (hasValue(linkDetail.getMerchantId())) {
            deeplinkBuilder.append(AMPERSAND_CHARACTER)
                    .append(DEEPLINK_MERCHANT_ID_KEY)
                    .append(EQUAL_CHARACTER)
                    .append(linkDetail.getMerchantId());
        }

        return new Deeplink(deeplinkBuilder.toString());
    }

}
