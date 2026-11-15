package com.caresync.backend.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Registered as a @Bean (not @Component) in SecurityConfig so it isn't also
 * auto-registered as a generic servlet filter and run twice.
 */
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final List<String> validApiKeys;

    public ApiKeyAuthFilter(String apiKeysCsv) {
        this.validApiKeys = apiKeysCsv.isBlank() ? List.of() : List.of(apiKeysCsv.split(","));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String apiKey = request.getHeader("X-API-Key");
        if (apiKey != null && validApiKeys.contains(apiKey)) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(apiKey, null, List.of()));
        }
        chain.doFilter(request, response);
    }
}
