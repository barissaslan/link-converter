package com.barisaslan.trendyollinkconverter.domain.builder.deeplink;

import com.barisaslan.trendyollinkconverter.domain.model.Deeplink;
import com.barisaslan.trendyollinkconverter.domain.model.LinkDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DefaultDeeplinkBuilderTest {

    @InjectMocks
    private DefaultDeeplinkBuilder defaultDeeplinkBuilder;

    @Test
    void shouldReturnHomePageDeeplink() {
        Deeplink deeplink = defaultDeeplinkBuilder.buildDeeplink(new LinkDetail());

        assertEquals("ty://?Page=Home", deeplink.getValue());
    }

}