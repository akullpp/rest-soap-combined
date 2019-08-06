package de.akull.geoloc.data;

import java.io.Serializable;
import lombok.Data;

@Data
public class GeolocationEntityId implements Serializable {

    private String query;

    private String address;
}
