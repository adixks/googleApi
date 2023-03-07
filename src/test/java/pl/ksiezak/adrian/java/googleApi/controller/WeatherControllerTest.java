package pl.ksiezak.adrian.java.googleApi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ksiezak.adrian.java.googleApi.entity.Location;
import pl.ksiezak.adrian.java.googleApi.service.LocationService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {
    @Mock
    private LocationService locationServiceMock;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    public void testSearchForLocation() throws Exception {
        //given
        String locationQueryString = "London";
        Location expectedLocation = new Location("London", 51.5074, -0.1278);

        when(locationServiceMock.searchForLocation(locationQueryString)).thenReturn(expectedLocation);

        //when
        ResponseEntity<Location> responseEntity = weatherController.searchForLocation(locationQueryString);

        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedLocation, responseEntity.getBody());
    }
}





