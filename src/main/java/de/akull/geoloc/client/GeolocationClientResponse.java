package de.akull.geoloc.client;

import java.util.List;

public interface GeolocationClientResponse {

    List<GeolocationResult> getResults();

    void setResults(List<GeolocationResult> results);
}
