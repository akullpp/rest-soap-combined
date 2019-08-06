package de.akull.geoloc.core;

import static org.springframework.data.domain.Sort.Direction.DESC;

import de.akull.geoloc.client.GeolocationClient;
import de.akull.geoloc.client.GeolocationClientResponse;
import de.akull.geoloc.data.GeolocationEntity;
import de.akull.geoloc.data.GeolocationRepository;
import de.akull.geoloc.exception.GeolocationValidationException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GeolocationService implements GeolocationPort {

    private final GeolocationClient geolocationClient;

    private final GeolocationRepository geolocationRepository;

    @Cacheable(value = "geolocations")
    public List<Geolocation> getGeolocationForQuery(String query) {
        if (query == null || query.isEmpty()) {
            throw new GeolocationValidationException("GEOLOCATION_EMPTY_QUERY");
        }
        List<GeolocationEntity> store = geolocationRepository.findAllByQuery(Base64.getEncoder().encodeToString(query.getBytes()));

        if (store.isEmpty()) {
            List<Geolocation> geolocations = getGeolocationsFromClient(query);
            return fromEntity(saveGeolocations(query, geolocations));
        }
        return fromEntity(store);
    }

    public List<Geolocation> getGeolocationsHistory(Integer limit) {
        if (limit == null) {
            return getAllGeolocations();
        }
        if (limit <= 0) {
            throw new GeolocationValidationException("GEOLOCATION_NEGATIVE_LIMIT");
        }
        return getLastGeolocations(limit);
    }

    private List<Geolocation> getGeolocationsFromClient(String query) {
        return fromClientResponse(query, geolocationClient.getGeolocationForQuery(query));
    }

    private List<GeolocationEntity> saveGeolocations(String query, List<Geolocation> geolocations) {
        return geolocationRepository.saveAll(toEntity(query, geolocations));
    }

    private List<Geolocation> getAllGeolocations() {
        return fromEntity(geolocationRepository.findAll());
    }

    private List<Geolocation> getLastGeolocations(Integer limit) {
        return fromEntity(geolocationRepository.findAll(PageRequest.of(0, limit, Sort.by(DESC, "id"))).getContent());
    }

    private List<GeolocationEntity> toEntity(String query, List<Geolocation> geolocations) {
        return geolocations.stream()
            .map(geolocation -> GeolocationEntity.builder()
                .query(Base64.getEncoder().encodeToString(geolocation.getQuery().getBytes()))
                .address(geolocation.getAddress())
                .latitude(geolocation.getLatitude())
                .longitude(geolocation.getLongitude())
                .build()
            )
            .collect(Collectors.toList());
    }

    private List<Geolocation> fromClientResponse(String query, GeolocationClientResponse response) {
        return response
            .getResults()
            .stream()
            .map(result -> Geolocation.builder()
                .query(query)
                .address(result.getAddress())
                .latitude(result.getLatitude())
                .longitude(result.getLongitude())
                .build()
            )
            .collect(Collectors.toList());
    }

    private List<Geolocation> fromEntity(List<GeolocationEntity> geolocationEntities) {
        return geolocationEntities.stream()
            .map(geolocationEntity -> Geolocation.builder()
                .query(new String(Base64.getDecoder().decode(geolocationEntity.getQuery())))
                .address(geolocationEntity.getAddress())
                .latitude(geolocationEntity.getLatitude())
                .longitude(geolocationEntity.getLongitude())
                .build()
            )
            .collect(Collectors.toList());
    }
}
