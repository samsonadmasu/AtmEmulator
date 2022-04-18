package com.Bank.Branch.Security;


import com.Bank.Branch.Dtos.AuthResDto;
import com.Bank.Branch.Utils.DateUtil;
import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthTokenIssuer {

    private final JwtConfig jwtConfig;
    private final SignatureAlgorithm signatureAlgorithm;

    public String issueAccessToken(User user){
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(DateUtil.dateAfter(jwtConfig.getAccessTokenExpirationTime()))
                .withIssuer(jwtConfig.getTokenIssuer())
                .withClaim(JwtConstants.CLAIM_NAME_ROLES,
                        user.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(signatureAlgorithm.sign());
    }

    public String issueRefreshToken(User user){
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(DateUtil.dateAfter(jwtConfig.getRefreshTokenExpirationTime()))
                .withClaim(JwtConstants.CLAIM_NAME_REFRESH_LIMIT, jwtConfig.getTokenRefreshLimit())
                .withClaim(JwtConstants.CLAIM_NAME_REFRESH_COUNT, jwtConfig.getTokenRefreshLimit())
                .withIssuer(jwtConfig.getTokenIssuer())
                .sign(signatureAlgorithm.sign());
    }

    public AuthResDto issueTokens(User user){
        String accessToken = issueAccessToken(user);
        String refreshToken = issueRefreshToken(user);
        return new AuthResDto(accessToken, refreshToken);
    }

}
