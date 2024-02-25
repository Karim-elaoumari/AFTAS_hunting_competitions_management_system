package com.aftas_backend.security.common.filter;

import com.aftas_backend.security.common.helper.RequestHelper;
import com.aftas_backend.security.common.jwt.JwtTokenService;
import com.aftas_backend.security.common.principal.UserPrincipalService;
import com.aftas_backend.security.utils.enums.EndPointType;
import com.aftas_backend.security.utils.enums.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final RequestHelper requestHelper;
    private final UserPrincipalService userPrincipalService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if (requestHelper.getEndPointType(request).equals(EndPointType.ACCESS)
                || requestHelper.getEndPointType(request).equals(EndPointType.AUTH)
        ) {

            String jwtToken = requestHelper.getJwtTokenIfExist(request);

            if (jwtToken == null) {
                filterChain.doFilter(request, response);
                return;
            }


            if (!jwtTokenService.isTokenValid(jwtToken, TokenType.ACCESS_TOKEN)) {
                filterChain.doFilter(request, response);
                return;
            }

            String username = jwtTokenService.extractUsername(jwtToken);
            setAuthentication(userPrincipalService.loadUserByUsername(username), request);
        }


        filterChain.doFilter(request, response);

    }

    private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }


}
