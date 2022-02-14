package com.barisaslan.trendyollinkconverter.common.filter;

import com.barisaslan.trendyollinkconverter.domain.model.ControllerCallLogDto;
import com.barisaslan.trendyollinkconverter.domain.service.ControllerCallLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CallLogFilterTest {

    @Mock
    private ControllerCallLogService controllerCallLogService;

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private FilterChain filterChainMock;

    @Test
    void testSomethingAboutDoFilter() throws ServletException, IOException {
        CallLogFilter filterUnderTest = new CallLogFilter(controllerCallLogService);

        filterUnderTest.doFilter(requestMock, responseMock, filterChainMock);

        verify(controllerCallLogService).create(any(ControllerCallLogDto.class));
    }

}