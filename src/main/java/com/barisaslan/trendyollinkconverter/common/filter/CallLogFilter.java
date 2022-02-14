package com.barisaslan.trendyollinkconverter.common.filter;

import com.barisaslan.trendyollinkconverter.domain.model.ControllerCallLogDto;
import com.barisaslan.trendyollinkconverter.domain.service.ControllerCallLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@Component
@RequiredArgsConstructor
public class CallLogFilter extends OncePerRequestFilter {

    private final ControllerCallLogService controllerCallLogService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long endTime = System.currentTimeMillis();

            ControllerCallLogDto dto = ControllerCallLogDto.builder()
                    .requestBody(wrappedRequest.getQueryString())
                    .responseBody(getResponsePayload(wrappedResponse))
                    .apiPath(request.getRequestURI())
                    .statusCode(wrappedResponse.getStatus())
                    .startTime(startTime)
                    .endTime(endTime)
                    .build();

            controllerCallLogService.create(dto);
        }

        wrappedResponse.copyBodyToResponse();
    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    return "[unknown]";
                }
            }
        }

        return "[unknown]";
    }

}
