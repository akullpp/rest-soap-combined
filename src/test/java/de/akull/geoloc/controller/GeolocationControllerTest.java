package de.akull.geoloc.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test", "default", "google"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeolocationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Description("Should return a valid response")
    void Should_Return_Valid_Response() {
        String query = "test123";

        ResponseEntity<GeolocationResource> result = restTemplate.getForEntity(String.format("/geoloc/%s", query), GeolocationResource.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}