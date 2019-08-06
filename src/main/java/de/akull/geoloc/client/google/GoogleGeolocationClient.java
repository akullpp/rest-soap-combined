package de.akull.geoloc.client.google;

import de.akull.geoloc.client.GeolocationClient;
import de.akull.geoloc.client.GeolocationClientResponse;
import de.akull.geoloc.exception.GeolocationClientException;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Profile("google")
public class GoogleGeolocationClient implements GeolocationClient {

    @Value("${google.geolocation.api}")
    private String api;

    @Value("${google.geolocation.key}")
    private String key;

    @Override
    public GeolocationClientResponse getGeolocationForQuery(String query) {
        RestTemplate template = new RestTemplate();
        UriBuilder builder = UriComponentsBuilder.fromUriString(api);
        builder.pathSegment("json");
        builder.queryParam("address", query);
        builder.queryParam("key", key);
        URI uri = builder.build();

        try {
            return template.getForObject(uri, GoogleGeolocationResponse.class);
        } catch (Exception e) {
            // TODO: Differentiate betweeen sensible errors.
            throw new GeolocationClientException(e);
        }
    }
}
