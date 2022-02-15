package com.barisaslan.trendyollinkconverter.domain.parser.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SearchUrlParserTest {

    @InjectMocks
    private SearchUrlParser searchUrlParser;

    @Test
    void shouldReturnSearchLinkDetail() throws MalformedURLException {
        LinkDetail linkDetail = searchUrlParser.parseUrl(new URL("https://www.trendyol.com/sr?q=kazak"));

        assertEquals(PageType.SEARCH_PAGE, linkDetail.getPageType());
        assertEquals("kazak", linkDetail.getSearchValue());
    }

}