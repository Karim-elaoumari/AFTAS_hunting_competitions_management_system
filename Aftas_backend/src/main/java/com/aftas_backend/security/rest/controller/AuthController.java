package com.aftas_backend.security.rest.controller;

import com.aftas_backend.security.common.principal.UserPrincipal;
import com.aftas_backend.security.common.principal.UserPrincipalService;
import com.aftas_backend.security.rest.dto.request.LoginRequest;
import com.aftas_backend.security.rest.dto.response.JwtAuthenticationResponse;
import com.aftas_backend.security.rest.dto.response.JwtRefreshTokenResponse;
import com.aftas_backend.security.rest.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserPrincipalService userPrincipalService;

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody @Valid LoginRequest registerRequest, HttpServletRequest httpServletRequest) {
        return authenticationService.login(registerRequest, httpServletRequest);
    }


    @GetMapping("/refresh")
    public JwtRefreshTokenResponse getNewAccessToken(HttpServletRequest httpServletRequest) {
        return authenticationService.refresh();
    }


    //for test
    @GetMapping("/token")
    public JwtAuthenticationResponse testToken(HttpServletRequest httpServletRequest) {
        return authenticationService.getTestToken(httpServletRequest);
    }

    //for test
    @GetMapping("/roles")
    public Collection<? extends GrantedAuthority> getRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    //for test
    @GetMapping("/principal")
    public UserPrincipal getPrincipal() {
        return userPrincipalService.getUserPrincipalFromContextHolder();
    }


}
