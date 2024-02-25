package com.aftas_backend.security.utils.env;

import com.aftas_backend.security.utils.enums.TokenType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
@Getter
public class SecurityEnvironment {
    private final Map<TokenType, Date> tokenExpirationDates;
    public SecurityEnvironment() {
        this.tokenExpirationDates = Map.of(
                TokenType.ACCESS_TOKEN, Date.from(Instant.now().plus(1, ChronoUnit.DAYS)),
                TokenType.REFRESH_TOKEN, Date.from(Instant.now().plus(7, ChronoUnit.DAYS))
        );
    }
}
