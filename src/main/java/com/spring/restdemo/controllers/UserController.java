package com.spring.restdemo.controllers;

import com.spring.restdemo.api.domain.User;
import com.spring.restdemo.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class UserController {
    private ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange) throws InterruptedException {


        Flux<User> userFlux = apiService.getUsers(serverWebExchange
                .getFormData()
                .map(data -> Integer.valueOf(data.getFirst("limit"))));
        model.addAttribute("users", userFlux);

        return "userlist";
    }
}
