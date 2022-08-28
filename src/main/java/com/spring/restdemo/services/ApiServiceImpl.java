package com.spring.restdemo.services;

import com.spring.restdemo.api.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private RestTemplate restTemplate;
    private final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }


    @Override
    public Flux<User> getUsers(Mono<Integer> limit) {
        return limit.flatMapMany(integer -> {
            return WebClient.create(api_url)
                    .get()
                    .uri(uriBuilder -> uriBuilder.queryParam("_limit", integer).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(User.class));
        });
    }
}
