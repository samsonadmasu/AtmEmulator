package com.Bank.Branch.Services;

import com.Bank.Branch.Dtos.AuthResDto;
import com.Bank.Branch.Security.AuthTokenIssuer;
import com.Bank.Branch.Security.AuthTokenParser;
import com.Bank.Branch.Security.RefreshTokenDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BankUserService bankUserService;
    private final AuthTokenIssuer authTokenIssuer;
//    private final AuthTokenParser authTokenParser;
    private final BCryptPasswordEncoder passwordEncoder;

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null){
            try {
                RefreshTokenDetails refreshTokenDetails = AuthTokenParser.parseRefreshToken(authorizationHeader);
                if(refreshTokenDetails == null){
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setHeader("Error", "expired token");
                    response.setStatus(FORBIDDEN.value());
                    Map<String,String> error = new HashMap<>();
                    error.put("error_message", "expired token");
                    response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }catch (Exception e){
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setHeader("Error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token invalid");
        }
    }

    public AuthResDto issueTokens(User user){
        return authTokenIssuer.issueTokens(user);
    }

}
