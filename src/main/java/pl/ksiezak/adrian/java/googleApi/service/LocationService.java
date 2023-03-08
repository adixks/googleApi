package pl.ksiezak.adrian.java.googleApi.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import pl.ksiezak.adrian.java.googleApi.entity.Location;
import pl.ksiezak.adrian.java.googleApi.repository.LocationRepository;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {
    private RestClient restClient;

    private final LocationRepository locationRepository;

    @Value("${google.maps.api.url}")
    private String googleMapsApiUrl;

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    @Value("${app.rest.template.enabled:true}")
    private boolean isRestTemplateEnabled;

    public Location searchForLocation(String locationQueryString) throws JSONException {
        String url = MessageFormat.format(googleMapsApiUrl, locationQueryString, googleMapsApiKey);
        Map<String, String> params = new HashMap<>();
        params.put("location", locationQueryString);
        params.put("apiKey", googleMapsApiKey);

        ResponseEntity<String> response = restClient.getForEntity(url, params);
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

    @PostConstruct
    public void init() {
        if (isRestTemplateEnabled) {
            restClient = new RestTemplateClientImpl(new RestTemplate());
        } else {
            restClient = new WebClientClientImpl(WebClient.builder().build());
        }
    }
}
