package com.spring.restdemo.services;

import com.spring.restdemo.api.domain.User;

import java.util.List;

public interface ApiService {
    List<User> getUsers(Integer limit);
}
