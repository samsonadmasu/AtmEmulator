package com.Bank.Branch.Http;


import com.Bank.Branch.Exceptions.RestTemplateResponseErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class HttpClient {

    public final RestTemplateResponseErrorHandler errorHandler;
    private final RestTemplateBuilder restTemplateBuilder;

    @Bean
    @LoadBalanced
    public RestTemplate get(){
        return restTemplateBuilder.errorHandler(errorHandler).build();
    }
}
