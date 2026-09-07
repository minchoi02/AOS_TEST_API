package com.aos.api.client;

import com.aos.api.dto.AosEventRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProviderApiClient {

    private final RestClient client;
    private final String token;

    public ProviderApiClient(
            @Value("${aos.provider.base-url}") String baseUrl,
            @Value("${aos.provider.token:}") String token) {
        this.client = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.token = token;
    }

    public String postEvent(AosEventRequest request) {
        return client.post()
                .uri("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> {
                    if (token != null && !token.isBlank()) {
                        h.setBearerAuth(token);
                    }
                })
                .body(request)
                .retrieve()
                .body(String.class);
    }

    public String getEvent(String receiptNo) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/events/{receiptNo}")
                        .build(receiptNo))
                .headers(h -> {
                    if (token != null && !token.isBlank()) {
                        h.setBearerAuth(token);
                    }
                })
                .retrieve()
                .body(String.class);
    }
}
