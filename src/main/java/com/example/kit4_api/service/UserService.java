package com.example.kit4_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
//    public boolean validateUser(String userId) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8081/users/validate" + userId;
//        return restTemplate.getForObject(url, Boolean.class);
//    }

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValidUser(String userId) {
        // Send request to "gestion des utilisateurs" API to validate user
        String url = "http://gestion-des-utilisateurs-api/users/validate?userId=" + userId;
        return restTemplate.getForObject(url, Boolean.class);
    }
}
