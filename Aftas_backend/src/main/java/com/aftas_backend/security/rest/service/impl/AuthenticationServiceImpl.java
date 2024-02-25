package com.aftas_backend.security.rest.service.impl;

import com.aftas_backend.security.common.jwt.JwtTokenService;
import com.aftas_backend.security.common.principal.UserPrincipalService;
import com.aftas_backend.security.rest.dto.request.LoginRequest;
import com.aftas_backend.security.rest.dto.response.JwtAuthenticationResponse;
import com.aftas_backend.security.rest.dto.response.JwtRefreshTokenResponse;
import com.aftas_backend.security.rest.service.AuthenticationService;
import com.aftas_backend.security.utils.enums.TokenType;
import com.aftas_backend.services.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserPrincipalService userPrincipalService;

    @Override
    public JwtAuthenticationResponse login(LoginRequest request, HttpServletRequest httpServletRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String access_token = jwtTokenService.generateToken(request.getUsername(), TokenType.ACCESS_TOKEN);
        String refresh_token = jwtTokenService.generateRefreshToken(request.getUsername(), httpServletRequest);

        return JwtAuthenticationResponse.builder()
                .accessToken(access_token)
                .refreshToken(refresh_token)
                .build();
    }

    @Override
    public JwtRefreshTokenResponse refresh() {
        String username =  userPrincipalService.getUserPrincipalFromContextHolder().getUsername();
       return  JwtRefreshTokenResponse.builder()
               .accessToken(jwtTokenService.generateToken(username, TokenType.ACCESS_TOKEN))
               .build();
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest) {

    }

    @Override
    public JwtAuthenticationResponse getTestToken(HttpServletRequest httpServletRequest) {
        return JwtAuthenticationResponse.builder()
                .accessToken(jwtTokenService.generateToken("1", TokenType.ACCESS_TOKEN))
                .refreshToken(jwtTokenService.generateRefreshToken("1", httpServletRequest))
                .build();
    }
}
