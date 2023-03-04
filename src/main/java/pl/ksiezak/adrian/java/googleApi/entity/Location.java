package pl.ksiezak.adrian.java.googleApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String queryString;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    public Location() {
    }

    public Location(String queryString, double latitude, double longitude) {
        this.queryString = queryString;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}