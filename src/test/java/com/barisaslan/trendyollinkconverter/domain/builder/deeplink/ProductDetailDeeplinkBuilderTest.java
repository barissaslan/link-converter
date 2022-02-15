package com.barisaslan.trendyollinkconverter.domain.builder.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductDetailDeeplinkBuilderTest {

    @InjectMocks
    private ProductDetailDeeplinkBuilder productDetailDeeplinkBuilder;

    @Test
    void shouldReturnProductDetailDeeplink() {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("78");

        Deeplink deeplink = productDetailDeeplinkBuilder.buildDeeplink(linkDetail);

        assertEquals("ty://?Page=Product&ContentId=78", deeplink.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWithCampaignId() {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("78");
        linkDetail.setBouqiqueId("55");

        Deeplink deeplink = productDetailDeeplinkBuilder.buildDeeplink(linkDetail);

        assertEquals("ty://?Page=Product&ContentId=78&CampaignId=55", deeplink.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWithMerchantId() {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("78");
        linkDetail.setMerchantId("33");

        Deeplink deeplink = productDetailDeeplinkBuilder.buildDeeplink(linkDetail);

        assertEquals("ty://?Page=Product&ContentId=78&MerchantId=33", deeplink.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWithMerchantIdAndCampaignId() {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("78");
        linkDetail.setMerchantId("22");
        linkDetail.setBouqiqueId("44");

        Deeplink deeplink = productDetailDeeplinkBuilder.buildDeeplink(linkDetail);

        assertEquals("ty://?Page=Product&ContentId=78&CampaignId=44&MerchantId=22", deeplink.getValue());
    }

}