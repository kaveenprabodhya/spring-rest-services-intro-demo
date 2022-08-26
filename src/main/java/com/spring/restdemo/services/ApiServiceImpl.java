package com.spring.restdemo.services;

import com.spring.restdemo.api.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        User[] userData = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users?_limit="+limit, User[].class);
        return Arrays.asList(userData);
    }
}
