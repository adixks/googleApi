package pl.ksiezak.adrian.java.googleApi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateClientImpl implements RestClient {
    private final RestTemplate restTemplate;

    public RestTemplateClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> getForEntity(String url, Map<String, String> params) {
        return restTemplate.getForEntity(url, String.class, params);
    }
}
