package com.barisaslan.trendyollinkconverter.domain.builder.url;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.WebUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DefaultUrlBuilderTest {

    @InjectMocks
    private DefaultUrlBuilder defaultUrlBuilder;

    @Test
    void shouldReturnHomePageUrl() {
        WebUrl webUrl = defaultUrlBuilder.buildUrl(new LinkDetail());

        assertEquals("https://www.trendyol.com", webUrl.getValue());
    }

}