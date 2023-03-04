package pl.ksiezak.adrian.java.googleApi.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ksiezak.adrian.java.googleApi.entity.Location;
import pl.ksiezak.adrian.java.googleApi.repository.LocationRepository;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class LocationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocationRepository locationRepository;

    @Value("${google.maps.api.url}")
    private String googleMapsApiUrl;

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    public Location searchForLocation(String locationQueryString) throws JSONException {
        String url = MessageFormat.format(googleMapsApiUrl, locationQueryString, googleMapsApiKey);
        Map<String, String> params = new HashMap<>();
        params.put("location", locationQueryString);
        params.put("apiKey", googleMapsApiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);
        Gson gson = new Gson();
        JsonElement json = gson.fromJson(response.getBody(), JsonElement.class);
        JsonObject location = json.getAsJsonObject()
                .get("results").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("geometry").getAsJsonObject()
                .get("location").getAsJsonObject();
        double lat = Double.parseDouble(location.get("lat").getAsString());
        double lng = Double.parseDouble(location.get("lng").getAsString());
        Location loc = new Location(locationQueryString, lat, lng);
        locationRepository.save(loc);
        return loc;
    }
}