package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductDetailDeeplinkParserTest {

    @InjectMocks
    private ProductDetailDeeplinkParser productDetailDeeplinkParser;

    @Test
    void shouldReturnSProductDetailLinkDetail() {
        HashMap<String, String> elementMap = new HashMap<>();
        elementMap.put(DEEPLINK_CONTENT_ID_KEY, "42");

        LinkDetail linkDetail = productDetailDeeplinkParser.parseDeeplink(elementMap);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("42", linkDetail.getContentId());
    }

    @Test
    void shouldReturnSProductDetailLinkDetailWithBoutiqueId() {
        HashMap<String, String> elementMap = new HashMap<>();
        elementMap.put(DEEPLINK_CONTENT_ID_KEY, "42");
        elementMap.put(DEEPLINK_BOUTIQUE_ID_KEY, "45");

        LinkDetail linkDetail = productDetailDeeplinkParser.parseDeeplink(elementMap);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("42", linkDetail.getContentId());
        assertEquals("45", linkDetail.getBouqiqueId());
    }

    @Test
    void shouldReturnSProductDetailLinkDetailWithMerchantId() {
        HashMap<String, String> elementMap = new HashMap<>();
        elementMap.put(DEEPLINK_CONTENT_ID_KEY, "42");
        elementMap.put(DEEPLINK_MERCHANT_ID_KEY, "48");

        LinkDetail linkDetail = productDetailDeeplinkParser.parseDeeplink(elementMap);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("42", linkDetail.getContentId());
        assertEquals("48", linkDetail.getMerchantId());
    }

    @Test
    void shouldReturnSProductDetailLinkDetailWithMerchantIdAndBoutiqueId() {
        HashMap<String, String> elementMap = new HashMap<>();
        elementMap.put(DEEPLINK_CONTENT_ID_KEY, "42");
        elementMap.put(DEEPLINK_BOUTIQUE_ID_KEY, "45");
        elementMap.put(DEEPLINK_MERCHANT_ID_KEY, "48");

        LinkDetail linkDetail = productDetailDeeplinkParser.parseDeeplink(elementMap);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("42", linkDetail.getContentId());
        assertEquals("45", linkDetail.getBouqiqueId());
        assertEquals("48", linkDetail.getMerchantId());
    }

}