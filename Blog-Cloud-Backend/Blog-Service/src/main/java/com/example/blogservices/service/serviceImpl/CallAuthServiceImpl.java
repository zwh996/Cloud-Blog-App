package com.example.blogservices.service.serviceImpl;

import com.example.blogservices.service.CallAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CallAuthServiceImpl implements CallAuthService {

    @Autowired
    RestTemplate restTemplate;
    @Override
    public boolean callAuth(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>("null", headers);
        log.info("Headers : " + requestEntity.getHeaders().toString());
        ResponseEntity callResult =  restTemplate.exchange("http://Auth-Service/api/user/auth",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Object>() {
                });
        boolean result = callResult.getStatusCodeValue() == 200 ? true : false;
        return result;
    }
}
