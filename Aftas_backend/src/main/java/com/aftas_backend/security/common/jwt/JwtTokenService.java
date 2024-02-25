package com.aftas_backend.security.common.jwt;

import com.aftas_backend.security.utils.enums.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Date;

public interface JwtTokenService {


    default Jws<Claims> claim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
    }


    public Key getSecretKey();


    public Boolean isTokenValid(String token, TokenType tokenType);

    public Boolean isMachType(String token, TokenType tokenType);

    public Boolean isMachSignature(String token);

    public Boolean isTokenExpired(String token);

    public String generateToken(String username, TokenType tokenType);

    String generateRefreshToken(String username, HttpServletRequest httpServletRequest);


    public Date extractExpiration(String token);

    public String extractUsername(String token);

    public TokenType extractTokenType(String token);


    String extractUserAgent(String jwtToken);

    String extractIpAddress(String jwtToken);
}
