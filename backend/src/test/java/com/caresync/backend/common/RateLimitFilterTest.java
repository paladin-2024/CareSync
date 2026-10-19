package com.caresync.backend.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RateLimitFilterTest {

    @Test
    void allowsRequestsUnderLimitAndBlocksOverLimit() throws Exception {
        RateLimitFilter filter = new RateLimitFilter(2, 60);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-API-Key")).thenReturn("test-key");
        FilterChain chain = mock(FilterChain.class);

        HttpServletResponse ok1 = mock(HttpServletResponse.class);
        filter.doFilter(request, ok1, chain);
        HttpServletResponse ok2 = mock(HttpServletResponse.class);
        filter.doFilter(request, ok2, chain);
        verify(chain, times(2)).doFilter(eq(request), any());

        HttpServletResponse blocked = mock(HttpServletResponse.class);
        StringWriter body = new StringWriter();
        when(blocked.getWriter()).thenReturn(new PrintWriter(body));
        filter.doFilter(request, blocked, chain);

        verify(blocked).setStatus(429);
        verify(chain, times(2)).doFilter(eq(request), any());
        assertEquals("{\"error\":\"Too Many Requests\"}", body.toString());
    }
}
