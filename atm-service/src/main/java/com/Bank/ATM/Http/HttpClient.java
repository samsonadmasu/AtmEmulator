package com.Bank.ATM.Http;



import com.Bank.ATM.Exceptions.RestTemplateResponseErrorHandler;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
