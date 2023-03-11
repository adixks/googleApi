package pl.ksiezak.adrian.java.googleApi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Map;

public class WebClientClientImpl implements RestClient {
    private final WebClient webClient;

    public WebClientClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ResponseEntity<String> getForEntity(String url, Map<String, String> params) {
        return webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
