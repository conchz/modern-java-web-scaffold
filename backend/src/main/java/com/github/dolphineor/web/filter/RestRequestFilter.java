package com.github.dolphineor.web.filter;

import com.google.common.collect.Lists;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 2016-01-22.
 *
 * @author dolphineor
 */
public class RestRequestFilter extends OncePerRequestFilter {

    private static List<String> staticResourcesSuffix =
            Lists.newArrayList("*.css", "js", "*.ico", "*.gif", "*.jpg", "*.jpeg", "*.png");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Filter static resources
        boolean isStaticResourcesRequest = checkStaticResourcesRequest(request);
        if (isStaticResourcesRequest) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Accept, Authorization");
        filterChain.doFilter(request, response);
    }

    private boolean checkStaticResourcesRequest(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        for (int i = 0, s = staticResourcesSuffix.size(); i < s; i++) {
            if (requestPath.contains(staticResourcesSuffix.get(i)))
                return true;
        }

        return false;
    }
}
