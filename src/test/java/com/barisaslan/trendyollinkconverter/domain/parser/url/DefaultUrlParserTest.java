package com.barisaslan.trendyollinkconverter.domain.parser.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.URL_HOME_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DefaultUrlParserTest {

    @InjectMocks
    private DefaultUrlParser defaultUrlParser;

    @Test
    void shouldReturnHomePageLinkDetail() throws MalformedURLException {
        LinkDetail linkDetail = defaultUrlParser.parseUrl(new URL(URL_HOME_PAGE));

        assertEquals(PageType.OTHER_PAGE, linkDetail.getPageType());
    }

}