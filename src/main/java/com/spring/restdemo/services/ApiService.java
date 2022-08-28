package com.spring.restdemo.services;

import com.spring.restdemo.api.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ApiService {
    Flux<User> getUsers(Mono<Integer> limit);
}
