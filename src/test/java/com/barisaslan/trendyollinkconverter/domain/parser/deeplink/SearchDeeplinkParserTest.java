package com.barisaslan.trendyollinkconverter.domain.parser.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import com.barisaslan.trendyollinkconverter.domain.model.PageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static com.barisaslan.trendyollinkconverter.common.constant.Constants.DEEPLINK_QUERY_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SearchDeeplinkParserTest {

    @InjectMocks
    private SearchDeeplinkParser searchDeeplinkParser;

    @Test
    void shouldReturnSearchPageLinkDetail() {
        HashMap<String, String> elementMap = new HashMap<>();
        elementMap.put(DEEPLINK_QUERY_KEY, "kazak");

        LinkDetail linkDetail = searchDeeplinkParser.parseDeeplink(elementMap);

        assertEquals(PageType.SEARCH_PAGE, linkDetail.getPageType());
        assertEquals("kazak", linkDetail.getSearchValue());
    }

}