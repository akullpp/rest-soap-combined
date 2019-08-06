package de.akull.geoloc.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import de.akull.geoloc.core.Geolocation;
import de.akull.geoloc.core.GeolocationPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@ApiController("geoloc")
@RequiredArgsConstructor
public class GeolocationController {

    private final GeolocationPort geolocationPort;

    @GetMapping("{city}")
    public ResponseEntity<Resources<GeolocationResource>> getGeolocationsForCity(@PathVariable String city) {
        return ResponseEntity.ok(new Resources<>(
            toResource(geolocationPort.getGeolocationForQuery(city)),
            linkTo(methodOn(GeolocationController.class).getGeolocationsForCity(city)).withSelfRel()
        ));
    }

    @GetMapping
    public ResponseEntity<Resources<GeolocationResource>> getLastGeolocations(@RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(new Resources<>(
            toResource(geolocationPort.getGeolocationsHistory(limit)),
            linkTo(methodOn(GeolocationController.class).getLastGeolocations(limit)).withSelfRel()
        ));
    }

    private List<GeolocationResource> toResource(List<Geolocation> geolocations) {
        return geolocations.stream()
            .map(geolocation -> {
                GeolocationResource resource = GeolocationResource.builder()
                    .query(geolocation.getQuery())
                    .address(geolocation.getAddress())
                    .latitude(geolocation.getLatitude())
                    .longitude(geolocation.getLongitude())
                    .build();
                resource.add(linkTo(methodOn(GeolocationController.class).getGeolocationsForCity(geolocation.getQuery())).withSelfRel());
                return resource;
            })
            .collect(Collectors.toList());
    }
}
