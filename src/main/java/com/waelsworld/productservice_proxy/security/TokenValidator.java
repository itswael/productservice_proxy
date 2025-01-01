package com.waelsworld.productservice_proxy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenValidator {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Optional<JwtObject> validateToken(String token) {
        try {
            JwtObject jwtObject = this.restTemplateBuilder.build().getForObject("http://localhost:8080/validateToken?token=" + token, JwtObject.class);
            return Optional.of(jwtObject);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
