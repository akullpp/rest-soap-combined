package de.akull.geoloc.exception;

public class GeolocationClientException extends GeolocationException {

    public GeolocationClientException(Throwable throwable) {
        super(503, "GEOLOCATION_CLIENT_UNAVAILABLE", throwable);
    }
}
