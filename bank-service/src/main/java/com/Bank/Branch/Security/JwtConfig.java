package com.Bank.Branch.Security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jwt.properties")
@Data
public class JwtConfig {

    @Value("${jwt.token.signature.secret}")
    private String tokenSignature;

    @Value("${jwt.token.refresh.limit}")
    private int tokenRefreshLimit;

    @Value("${jwt.token.expiration.time.time}")
    private String accessTokenExpirationTime;

    @Value("${jwt.token.refresh.expiration.time}")
    private String refreshTokenExpirationTime;

    @Value("${jwt.token.issuer}")
    private String tokenIssuer;
}
