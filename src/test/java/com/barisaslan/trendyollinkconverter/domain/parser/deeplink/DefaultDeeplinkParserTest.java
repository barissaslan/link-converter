package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DefaultDeeplinkParserTest {

    @InjectMocks
    private DefaultDeeplinkParser defaultDeeplinkParser;

    @Test
    void shouldReturnHomePageLinkDetail() {
        LinkDetail linkDetail = defaultDeeplinkParser.parseDeeplink(new HashMap<>());

        assertEquals(PageType.OTHER_PAGE, linkDetail.getPageType());
    }

}