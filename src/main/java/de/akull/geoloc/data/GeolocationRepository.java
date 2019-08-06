package de.akull.geoloc.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationRepository extends JpaRepository<GeolocationEntity, Long> {

    List<GeolocationEntity> findAllByQuery(String query);
}