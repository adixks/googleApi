package pl.ksiezak.adrian.java.googleApi.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RestClient {
    ResponseEntity<String> getForEntity(String url, Map<String, String> params);
}
