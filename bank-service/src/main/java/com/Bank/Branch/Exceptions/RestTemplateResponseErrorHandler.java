package com.Bank.Branch.Exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        return (
                response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
                );
        //Handle server error
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if(response.getStatusCode().is4xxClientError() ||
           response.getStatusCode().is5xxServerError()){

            try(BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getBody())
            )) {
                String httpBodyResponse = reader.lines()
                        .collect(Collectors.joining(""));

                ObjectMapper mapper = new ObjectMapper();
                RestTemplateError restTemplateError = mapper.readValue(httpBodyResponse, RestTemplateError.class);
                throw new ResponseStatusException(response.getStatusCode(), restTemplateError.getMessage());
            }
        }
    }

}
