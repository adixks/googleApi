package pl.ksiezak.adrian.java.googleApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ksiezak.adrian.java.googleApi.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
