package com.barisaslan.trendyollinkconverter.domain.service;

import com.barisaslan.trendyollinkconverter.common.exception.InvalidDeeplinkException;
import com.barisaslan.trendyollinkconverter.common.exception.UrlBuildException;
import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeeplinkConverterServiceTest {

    @InjectMocks
    private DeeplinkConverterServiceImpl deeplinkConverterService;

    @Test
    void shouldReturnHomePagewebUrlWhenGivenOtherPageDeeplink() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Favorites");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com", webUrl.getValue());
    }

    @Test
    void shouldReturnHomePageDeeplinkWhenGivenAnotherOtherPageDeeplink() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Orders");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com", webUrl.getValue());
    }

    @Test
    void shouldReturnSearchQueryUrlWhenGivenSearchDeeplink() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Search&Query=elbise");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com/sr?q=elbise", webUrl.getValue());
    }

    @Test
    void shouldReturnSearchQueryUrlWhenGivenNonAsciiSearchDeeplink() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Search&Query=%C3%BCt%C3%BC");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC", webUrl.getValue());
    }

    @Test
    void shouldReturnProductDetailUrlWhenGivenProductDetailDeeplink() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Product&ContentId=1925865");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com/brand/name-p-1925865", webUrl.getValue());
    }

    @Test
    void shouldReturnProductDetailUrlWhenGivenProductDetailDeeplinkWithCampaignId() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Product&ContentId=1925865&CampaignId=439892");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892", webUrl.getValue());
    }

    @Test
    void shouldReturnProductDetailUrlWhenGivenProductDetailDeeplinkWithMerchantId() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Product&ContentId=1925865&MerchantId=105064");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064", webUrl.getValue());
    }

    @Test
    void shouldReturnProductDetailUrlWhenGivenProductDetailDeeplinkWithMerchantIdAndCampaignId() throws InvalidDeeplinkException, UrlBuildException {
        // given
        Deeplink deeplink = new Deeplink("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        // when
        WebUrl webUrl = deeplinkConverterService.convertDeeplinkToUrl(deeplink);

        // then
        assertEquals("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064", webUrl.getValue());
    }

    @Test
    void shouldThrowInvalidDeeplinkExceptionWhenGivenInvalidDeeplink() {
        // given
        Deeplink deeplink = new Deeplink("ty:/htp/asd");

        // when
        InvalidDeeplinkException thrown = assertThrows(
                InvalidDeeplinkException.class,
                () -> deeplinkConverterService.convertDeeplinkToUrl(deeplink)
        );

        // then
        assertNotNull(thrown.getMessage());
    }

    @Test
    void shouldThrowInvalidDeeplinkExceptionWhenGivenNonTrendyolDeeplink() {
        // given
        Deeplink deeplink = new Deeplink("twitter://?Page=Home");

        // when
        InvalidDeeplinkException thrown = assertThrows(
                InvalidDeeplinkException.class,
                () -> deeplinkConverterService.convertDeeplinkToUrl(deeplink)
        );

        // then
        assertNotNull(thrown.getMessage());
    }

}