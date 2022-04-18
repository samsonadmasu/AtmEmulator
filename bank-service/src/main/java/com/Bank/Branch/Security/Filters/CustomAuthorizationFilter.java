package com.Bank.Branch.Security.Filters;

import com.Bank.Branch.Security.AuthTokenParser;
import com.Bank.Branch.Security.AuthenticationTokenDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@NoArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

//    private AuthTokenParser authTokenParser;
//
//    @Autowired
//    public CustomAuthorizationFilter(AuthTokenParser authTokenParser){
//        this.authTokenParser = authTokenParser;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().equals("/auth/login") || request.getServletPath().equals("auth/refresh_token")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null) {
                try {
                    //"Bearer "
                    String token = authorizationHeader.substring(7);
                    System.out.println("Token========> " + token);
                    AuthenticationTokenDetails tokenDetails = AuthTokenParser.parseAccessToken(token);

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(tokenDetails.getUsername(), null, null);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    System.out.println("Error logging in " + e.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setHeader("error", e.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    response.sendError(FORBIDDEN.value());
                    //Map<String, String> error = new HashMap<>();
                   // error.put("error_message", e.getMessage());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}

