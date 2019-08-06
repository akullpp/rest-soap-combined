package de.akull.geoloc.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import de.akull.geoloc.client.GeolocationClient;
import de.akull.geoloc.data.GeolocationRepository;
import de.akull.geoloc.exception.GeolocationValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GeolocationServiceTest {

    @Mock
    GeolocationClient client;

    @Mock
    GeolocationRepository repository;

    @Test
    void Should_Throw_If_Parameter_Is_Null() {
        GeolocationService service = new GeolocationService(client, repository);

        assertThatThrownBy(() -> service.getGeolocationForQuery(null))
            .isInstanceOf(GeolocationValidationException.class);
    }

    @Test
    void Should_Throw_If_Parameter_Is_Empty_String() {
        GeolocationService service = new GeolocationService(client, repository);

        assertThatThrownBy(() -> service.getGeolocationForQuery(""))
            .isInstanceOf(GeolocationValidationException.class);
    }

    @Test
    void Should_Retrieve_From_Repository_If_Available() {}

    @Test
    void Should_Retrieve_From_Client_If_Repository_Not_Available() {}
}