package com.Bank.Branch.Security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


import java.util.*;


public class AuthTokenParser {

    //private final SignatureAlgorithm signatureAlgorithm;
    private static final String signature = "5817b83286e91337b65c7a35d05fd81f4a4e0314";

    private static Algorithm algorithm = Algorithm.HMAC256(signature);

    public static AuthenticationTokenDetails parseAccessToken(String token){

        try{
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            List<String> StringRoles = decodedJWT.getClaim(JwtConstants.CLAIM_NAME_ROLES).asList(String.class);
            String issuer = decodedJWT.getIssuer();
            Date issuedDate = decodedJWT.getIssuedAt();
            Date expirationDate = decodedJWT.getExpiresAt();

            return AuthenticationTokenDetails.builder()
                    .username(username)
                    .issuer(issuer)
                    .issuedDate(issuedDate)
                    .expirationDate(expirationDate)
                    .build();

        }catch (Exception e){
            //Todo: log to lostash
            e.printStackTrace();
            return null;
        }
    }

    public static RefreshTokenDetails parseRefreshToken(String token){
        try{
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            String issuer = decodedJWT.getIssuer();
            Date issuedDate = decodedJWT.getIssuedAt();
            Date expirationDate = decodedJWT.getExpiresAt();
            int refreshCount = decodedJWT.getClaim(JwtConstants.CLAIM_NAME_REFRESH_COUNT).asInt();
            int refreshLimit = decodedJWT.getClaim(JwtConstants.CLAIM_NAME_REFRESH_LIMIT).asInt();

            if(refreshCount < refreshLimit){
                return RefreshTokenDetails.builder()
                        .username(username)
                        .issuer(issuer)
                        .refreshCount(refreshCount)
                        .refreshLimit(refreshLimit)
                        .issuedDate(issuedDate)
                        .expirationDate(expirationDate)
                        .build();
            } else {
                throw new RuntimeException("Your refresh token has been expired. Please login again.");
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
