package de.akull.geoloc.client.google;


import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GoogleGeolocationClientTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
    }

    @AfterEach
    void destroy() {
        mapper = null;
    }

    @Test
    @DisplayName("Should convert from Google format to Geolocation")
    void Should_Convert_From_Google_Format_To_Geolocation() throws Exception {
        String json = "{\"results\": [ { \"formatted_address\": \"Test 1234\", \"geometry\": { \"location\": { \"lat\": 1.0001, \"lng\": 2.0002 } } } ] }";

        GoogleGeolocationResponse result = mapper.readValue(json, GoogleGeolocationResponse.class);

        assertThat(result).hasFieldOrProperty("results");
        assertThat(result.getResults().get(0)).hasFieldOrPropertyWithValue("address", "Test 1234");
        assertThat(result.getResults().get(0)).hasFieldOrPropertyWithValue("latitude", "1.0001");
        assertThat(result.getResults().get(0)).hasFieldOrPropertyWithValue("longitude", "2.0002");
    }
}