package de.akull.geoloc.exception;

import lombok.Getter;

@Getter
public class GeolocationException extends RuntimeException {

    private int status;

    private String key;

    private Throwable throwable;

    public GeolocationException() {
        this.status = 500;
        this.key = "GEOLOCATION_UNKNOWN_FAILURE";
    }

    public GeolocationException(Throwable throwable) {
        this.status = 500;
        this.key = "GEOLOCATION_UNKNOWN_FAILURE";
        this.throwable = throwable;
    }

    public GeolocationException(String key) {
        this.key = key;
    }

    public GeolocationException(String key, Throwable throwable) {
        this.status = 500;
        this.key = key;
        this.throwable = throwable;
    }

    public GeolocationException(int status) {
        this.status = status;
    }

    public GeolocationException(int status, Throwable throwable) {
        this.status = status;
        this.throwable = throwable;
        this.key = "GEOLOCATION_UNKNOWN_FAILURE";
    }

    public GeolocationException(int status, String key) {
        this.status = status;
        this.key = key;
    }

    public GeolocationException(int status, String key, Throwable throwable) {
        this.status = status;
        this.key = key;
        this.throwable = throwable;
    }

    public boolean hasThrowable() {
        return this.throwable != null;
    }
}
