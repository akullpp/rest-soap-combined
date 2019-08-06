package de.akull.geoloc.soap;

import de.akull.geoloc.exception.GeolocationException;
import javax.xml.ws.WebFault;
import lombok.Getter;

@Getter
@WebFault
public class GeolocationFault extends GeolocationException {

    private String faultInfo;

    public GeolocationFault(String key) {
        this.faultInfo = key;
    }
}
