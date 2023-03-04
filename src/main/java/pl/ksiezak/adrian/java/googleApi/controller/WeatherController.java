package pl.ksiezak.adrian.java.googleApi.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.ksiezak.adrian.java.googleApi.entity.Location;
import pl.ksiezak.adrian.java.googleApi.service.LocationService;

@RestController
public class WeatherController implements WeatherControllerAPI {

    @Autowired
    private LocationService locationService;

    @Override
    public ResponseEntity<Location> searchForLocation(String locationQueryString) {
        Location location;
        try {
            location = locationService.searchForLocation(locationQueryString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }
}