package pl.ksiezak.adrian.java.googleApi.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ksiezak.adrian.java.googleApi.entity.Location;
import pl.ksiezak.adrian.java.googleApi.repository.LocationRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class LocationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocationRepository locationRepository;

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    public Location searchForLocation(String locationQueryString) throws JSONException {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address={location}&key={apiKey}";
        Map<String, String> params = new HashMap<>();
        params.put("location", locationQueryString);
        params.put("apiKey", googleMapsApiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);
        JSONObject json = new JSONObject(response.getBody());
        JSONObject location = json.getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");
        double lat = location.getDouble("lat");
        double lng = location.getDouble("lng");
        Location loc = new Location(locationQueryString, lat, lng);
        locationRepository.save(loc);
        return loc;
    }
}

