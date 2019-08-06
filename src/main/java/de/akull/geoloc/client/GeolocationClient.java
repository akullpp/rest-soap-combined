package de.akull.geoloc.client;

import org.springframework.stereotype.Service;

@Service
public interface GeolocationClient<T extends GeolocationClientResponse> {

    T getGeolocationForQuery(String query);
}
