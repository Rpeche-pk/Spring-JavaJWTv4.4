package com.lpa.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilterAuth extends OncePerRequestFilter {

    private final JwtUtilsProvider jwtUtilsProvider;

    public JwtFilterAuth(JwtUtilsProvider jwtUtilsProvider) {
        this.jwtUtilsProvider = jwtUtilsProvider;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] bearerToken = header.split(" ");
            if (bearerToken.length == 2 && "Bearer".equals(bearerToken[0])) {
                String token = bearerToken[1].strip();
                try {
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(jwtUtilsProvider.validToken(token));

                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
