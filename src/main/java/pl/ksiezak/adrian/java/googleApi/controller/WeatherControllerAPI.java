package pl.ksiezak.adrian.java.googleApi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ksiezak.adrian.java.googleApi.entity.Location;

@RequestMapping(value = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public interface WeatherControllerAPI {
    @GetMapping("/location/{locationQueryString}")
    ResponseEntity<Location> searchForLocation(@PathVariable String locationQueryString);
}


