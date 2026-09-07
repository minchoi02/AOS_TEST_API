package com.aos.api.client;

import com.aos.api.dto.kakao.KakaoEventRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoApiClient {

    private final RestTemplate restTemplate;

    @Value("${kakao.api.base-url:http://localhost:9090}")
    private String baseUrl;

    @Value("${kakao.api.token:}")
    private String token;

    public KakaoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendEvent(KakaoEventRequest request) {

        String url = baseUrl + "/api/v1/kakao/events";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (token != null && !token.isBlank()) {
            headers.setBearerAuth(token);
        }

        HttpEntity<KakaoEventRequest> entity =
                new HttpEntity<>(request, headers);

        return restTemplate.postForObject(
                url,
                entity,
                String.class
        );
    }
}