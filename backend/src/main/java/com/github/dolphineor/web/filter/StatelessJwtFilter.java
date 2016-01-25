package com.github.dolphineor.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by dolphineor on 2016-01-25.
 *
 * @author dolphineor
 */
public class StatelessJwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Objects.equals("/api/user/login", request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);

//        final String authHeader = request.getHeader("Authorization");
//        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
//            throw new ServletException("Missing or invalid Authorization header.");
//        }
//
//        if (Objects.equals("/api/user/logout", request.getRequestURI())) {
//            filterChain.doFilter(request, response);
//            return;
//        }
    }
}
