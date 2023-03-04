package pl.ksiezak.adrian.java.googleApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ksiezak.adrian.java.googleApi.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}