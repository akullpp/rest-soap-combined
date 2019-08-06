package de.akull.geoloc.controller;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Getter
@Builder
@Relation(collectionRelation = "geolocations")
class GeolocationResource extends ResourceSupport {

    @NotBlank
    private String query;

    @NotBlank
    private String address;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;
}
