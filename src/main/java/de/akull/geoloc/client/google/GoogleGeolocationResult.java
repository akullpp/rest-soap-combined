package de.akull.geoloc.client.google;

import de.akull.geoloc.client.GeolocationResult;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Profile;

@Getter
@Builder
@Profile("google")
public class GoogleGeolocationResult implements GeolocationResult {

    private String address;

    private String longitude;

    private String latitude;
}
