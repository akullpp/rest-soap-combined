package de.akull.geoloc.core;

import java.util.List;

public interface GeolocationPort {

    List<Geolocation> getGeolocationForQuery(String query);

    List<Geolocation> getGeolocationsHistory(Integer limit);
}
