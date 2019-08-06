package de.akull.geoloc.client.google;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.akull.geoloc.client.GeolocationClientResponse;
import de.akull.geoloc.client.GeolocationResult;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize(using = GoogleGeolocationDeserializer.class)
class GoogleGeolocationResponse implements GeolocationClientResponse {

    private List<GeolocationResult> results;
}
