package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SearchUrlBuilderTest {

    @InjectMocks
    private SearchUrlBuilder searchUrlBuilder;

    @Test
    void shouldReturnSearchPageUrl() {
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setSearchValue("kazak");

        WebUrl webUrl = searchUrlBuilder.buildUrl(linkDetail);

        assertEquals("https://www.trendyol.com/sr?q=kazak", webUrl.getValue());
    }

}