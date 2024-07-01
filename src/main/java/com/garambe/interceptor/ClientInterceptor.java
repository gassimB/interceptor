package com.garambe.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
public class ClientInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ClientInterceptor.class);
    private final RestClient restClient;

    public ClientInterceptor(RestClient.Builder builder, ClientHttpRequestInterceptor restInterceptor) {
        this.restClient = builder
                .baseUrl("https://catfact.ninja/")
                .requestFactory(new JdkClientHttpRequestFactory())
                //.requestInterceptor(restInterceptor) // version 1
                .requestInterceptor((request, body, execution) -> {
                    logger.info("url intercepted version 2 : " + request.getURI());
                    request.getHeaders().add("x-request-id", "857555555");
                    return execution.execute(request, body);
                })
                .build();

    }

    public String getAllItems() {
        return  restClient.get()
                .uri("fact")
                .retrieve()
                .body(String.class);
    }
}
