package com.Bank.ATM.Http;


import com.Bank.ATM.Interfaces.IHttpMethods;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class RequestHandler implements IHttpMethods {

    private final HttpClient httpClient;
    private final String serviceName;

    public RequestHandler(HttpClient httpClient, String serviceName){
        this.httpClient = httpClient;
        this.serviceName = serviceName;
    }

    @Override
    public <T> List<T> getList(String url, Object param) {
        ParameterizedTypeReference<List<T>> responseType =
                new ParameterizedTypeReference<>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                };
        return  httpClient.get().exchange(urlBuilder(url), HttpMethod.GET, null, responseType, param).getBody();
    }



    @Override
    public <T> T get(String url, Class<T> responseType, Object param) {
        return httpClient.get().getForObject(urlBuilder(url), responseType , param);
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, String> params) {
        return httpClient.get().getForObject(urlBuilder(url), responseType , params);
    }

    @Override
    public <T> List<T> getList(String url, Map<String, String> params) {
        ParameterizedTypeReference<List<T>> responseType =
                new ParameterizedTypeReference<>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                };
        return  httpClient.get().exchange(urlBuilder(url), HttpMethod.GET, null, responseType, params).getBody();
    }

    @Override
    public <T> T post(String url, Class<T> responseType, Object requestObject) {
        return (T) httpClient.get().postForObject(urlBuilder(url), requestObject,responseType,new Object());
    }

    public <T> T post(String url, Class<T> resposeType, Map<String, String> params){
        return httpClient.get().postForObject(urlBuilder(url),null, resposeType, params);
    }

    public <T> List<T> getList(String url) {
        ParameterizedTypeReference<List<T>> responseType =
                new ParameterizedTypeReference<>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                };
        return  httpClient.get().exchange(urlBuilder(url), HttpMethod.GET, null, responseType).getBody();
    }




    private String urlBuilder(String url){
        String URL_STARTER = "http://%s/";
        return String.format(URL_STARTER,serviceName) + url;
    }


}
