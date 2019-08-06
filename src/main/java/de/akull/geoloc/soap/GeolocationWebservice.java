package de.akull.geoloc.soap;

import de.akull.geoloc.core.Geolocation;
import de.akull.geoloc.core.GeolocationPort;
import de.akull.geoloc.exception.GeolocationException;
import java.util.List;
import java.util.stream.Collectors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@WebService
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationWebservice {

    private GeolocationPort geolocationPort;

    @WebMethod
    public List<GeolocationResponse> getGeolocationsForCity(@WebParam(name = "query") String city) throws GeolocationFault {
        try {
            return toResponse(geolocationPort.getGeolocationForQuery(city));
        } catch (GeolocationException exception) {
            throw new GeolocationFault(exception.getKey());
        }
    }

    @WebMethod
    public List<GeolocationResponse> getGeolocationHistory(@WebParam(name = "limit") Integer limit) {
        try {
            return toResponse(geolocationPort.getGeolocationsHistory(limit));
        } catch (GeolocationException exception) {
            throw new GeolocationFault(exception.getKey());
        }
    }

    private List<GeolocationResponse> toResponse(List<Geolocation> geolocations) {
        return geolocations.stream()
            .map(geolocation -> GeolocationResponse.builder()
                .query(geolocation.getQuery())
                .address(geolocation.getAddress())
                .latitude(geolocation.getLatitude())
                .longitude(geolocation.getLongitude())
                .build()
            )
            .collect(Collectors.toList());
    }
}
