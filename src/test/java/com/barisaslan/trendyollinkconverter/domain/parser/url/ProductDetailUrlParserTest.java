package com.barisaslan.trendyollinkconverter.domain.parser.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductDetailUrlParserTest {

    @InjectMocks
    private ProductDetailUrlParser productDetailUrlParser;

    @Test
    void shouldReturnProductDetailLinkDetail() throws MalformedURLException {
        URL url = new URL("https://www.trendyol.com/brand/name-p-33");

        LinkDetail linkDetail = productDetailUrlParser.parseUrl(url);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("33", linkDetail.getContentId());
    }

    @Test
    void shouldReturnProductDetailLinkDetailWithBouqiueId() throws MalformedURLException {
        URL url = new URL("https://www.trendyol.com/brand/name-p-33?boutiqueId=44");

        LinkDetail linkDetail = productDetailUrlParser.parseUrl(url);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("33", linkDetail.getContentId());
        assertEquals("44", linkDetail.getBouqiqueId());
    }

    @Test
    void shouldReturnProductDetailLinkDetailWithMerchantId() throws MalformedURLException {
        URL url = new URL("https://www.trendyol.com/brand/name-p-33?merchantId=55");

        LinkDetail linkDetail = productDetailUrlParser.parseUrl(url);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("33", linkDetail.getContentId());
        assertEquals("55", linkDetail.getMerchantId());
    }

    @Test
    void shouldReturnProductDetailLinkDetailWithMerchantIdAndBoutiqueId() throws MalformedURLException {
        URL url = new URL("https://www.trendyol.com/brand/name-p-33?merchantId=55&boutiqueId=44");

        LinkDetail linkDetail = productDetailUrlParser.parseUrl(url);

        assertEquals(PageType.PRODUCT_DETAIL_PAGE, linkDetail.getPageType());
        assertEquals("33", linkDetail.getContentId());
        assertEquals("44", linkDetail.getBouqiqueId());
        assertEquals("55", linkDetail.getMerchantId());
    }

}