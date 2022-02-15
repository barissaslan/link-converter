package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductDetailUrlBuilderTest {

    @InjectMocks
    private ProductDetailUrlBuilder productDetailUrlBuilder;

    @Test
    void shouldReturnProductDetailDeeplink() throws UrlBuildException {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("11");

        WebUrl webUrl = productDetailUrlBuilder.buildUrl(linkDetail);

        assertEquals("https://www.trendyol.com/brand/name-p-11", webUrl.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWithCampaignId() throws UrlBuildException {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("11");
        linkDetail.setBouqiqueId("22");

        WebUrl webUrl = productDetailUrlBuilder.buildUrl(linkDetail);

        assertEquals("https://www.trendyol.com/brand/name-p-11?boutiqueId=22", webUrl.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWithMerchantId() throws UrlBuildException {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("11");
        linkDetail.setMerchantId("33");

        WebUrl webUrl = productDetailUrlBuilder.buildUrl(linkDetail);

        assertEquals("https://www.trendyol.com/brand/name-p-11?merchantId=33", webUrl.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWithMerchantIdAndCampaignId() throws UrlBuildException {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setContentId("11");
        linkDetail.setBouqiqueId("22");
        linkDetail.setMerchantId("33");

        WebUrl webUrl = productDetailUrlBuilder.buildUrl(linkDetail);

        assertEquals("https://www.trendyol.com/brand/name-p-11?boutiqueId=22&merchantId=33", webUrl.getValue());
    }

}