package com.aftas_backend.security.rest.service;

import com.aftas_backend.security.rest.dto.request.LoginRequest;
import com.aftas_backend.security.rest.dto.response.JwtAuthenticationResponse;
import com.aftas_backend.security.rest.dto.response.JwtRefreshTokenResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {


    JwtAuthenticationResponse login(LoginRequest request, HttpServletRequest httpServletRequest);

    JwtRefreshTokenResponse refresh();

    void logout(HttpServletRequest httpServletRequest);

    JwtAuthenticationResponse getTestToken(HttpServletRequest httpServletRequest);
}