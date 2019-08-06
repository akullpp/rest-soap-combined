package de.akull.geoloc.exception;

public class GeolocationValidationException extends GeolocationException {

    public GeolocationValidationException(String key) {
        super(400, key);
    }

    public GeolocationValidationException(String key, Throwable throwable) {
        super(400, key, throwable);
    }
}
