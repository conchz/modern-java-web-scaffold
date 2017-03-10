package org.lavenderx.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created on 2016-01-25.
 *
 * @author lavenderx
 */
public class StatelessJwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if (Objects.equals("/api/user/login", request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }


        final String authHeader = request.getHeader("Authorization");
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            throw new JwtException("Missing or invalid Authorization header.");
        }


        final String token = authHeader.substring(7);

        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey("secretkey")
                    .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        } catch (JwtException e) {
            throw new JwtException("Invalid token.");
        }

        filterChain.doFilter(request, response);
    }
}
