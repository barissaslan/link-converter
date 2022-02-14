package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidUrlException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UrlConverterServiceTest {

    @InjectMocks
    private UrlConverterServiceImpl urlConverterService;

    @Test
    void shouldReturnHomePageDeeplinkWhenGivenOtherPageUrl() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/Hesabim/Favoriler");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Home", deeplink.getValue());
    }

    @Test
    void shouldReturnHomePageDeeplinkWhenGivenAnotherOtherPageUrl() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/Hesabim/#/Siparislerim");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Home", deeplink.getValue());
    }

    @Test
    void shouldReturnSearchQueryDeeplinkWhenGivenSearchUrl() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/sr?q=elbise");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Search&Query=elbise", deeplink.getValue());
    }

    @Test
    void shouldReturnSearchQueryDeeplinkWhenGivenNonAsciiSearchUrl() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Search&Query=%C3%BCt%C3%BC", deeplink.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWhenGivenProductDetailUrl() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Product&ContentId=1925865", deeplink.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWhenGivenProductDetailUrlWithBoutiqueId() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Product&ContentId=1925865&CampaignId=439892", deeplink.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWhenGivenProductDetailUrlWithMerchantId() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Product&ContentId=1925865&MerchantId=105064", deeplink.getValue());
    }

    @Test
    void shouldReturnProductDetailDeeplinkWhenGivenProductDetailUrlWithMerchantIdAndBouqiueId() throws InvalidUrlException {
        // given
        WebUrl webUrl = new WebUrl("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064");

        // when
        Deeplink deeplink = urlConverterService.convertUrlToDeeplink(webUrl);

        // then
        assertEquals("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", deeplink.getValue());
    }

    @Test
    void shouldThrowInvalidUrlExceptionWhenGivenInvalidUrl()  {
        // given
        WebUrl webUrl = new WebUrl("htp/asd");

        // when
        InvalidUrlException thrown = assertThrows(
                InvalidUrlException.class,
                () -> urlConverterService.convertUrlToDeeplink(webUrl)
        );

        // then
        assertNotNull(thrown.getMessage());
    }

    @Test
    void shouldThrowInvalidUrlExceptionWhenGivenNonTrendyolUrl()  {
        // given
        WebUrl webUrl = new WebUrl("https://test.com/Hesabim/Favoriler");

        // when
        InvalidUrlException thrown = assertThrows(
                InvalidUrlException.class,
                () -> urlConverterService.convertUrlToDeeplink(webUrl)
        );

        // then
        assertNotNull(thrown.getMessage());
    }

}