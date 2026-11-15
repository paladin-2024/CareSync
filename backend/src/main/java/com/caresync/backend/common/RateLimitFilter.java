package com.caresync.backend.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fixed-window limiter per client key (API key if present, else remote IP).
 * Registered as a @Bean (not @Component) in SecurityConfig so it isn't also
 * auto-registered as a generic servlet filter and run twice.
 * shortcut: in-memory, so limits reset on restart and aren't shared across instances; upgrade to Redis if scaled out.
 */
public class RateLimitFilter extends OncePerRequestFilter {

    private final int maxRequestsPerWindow;
    private final long windowMillis;
    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    public RateLimitFilter(int maxRequestsPerWindow, long windowSeconds) {
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.windowMillis = windowSeconds * 1000;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String key = clientKey(request);
        Window window = windows.computeIfAbsent(key, k -> new Window(System.currentTimeMillis()));

        long now = System.currentTimeMillis();
        synchronized (window) {
            if (now - window.start > windowMillis) {
                window.start = now;
                window.count.set(0);
            }
            if (window.count.incrementAndGet() > maxRequestsPerWindow) {
                response.setStatus(429);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Too Many Requests\"}");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private String clientKey(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        return apiKey != null ? apiKey : request.getRemoteAddr();
    }

    private static final class Window {
        private volatile long start;
        private final AtomicInteger count = new AtomicInteger(0);

        private Window(long start) {
            this.start = start;
        }
    }
}
