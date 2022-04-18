package com.Bank.Branch.Security;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SignatureAlgorithm {

    private final JwtConfig jwtConfig;

    @Bean
    public Algorithm sign(){
        return Algorithm.HMAC256(jwtConfig.getTokenSignature());
    }
}
