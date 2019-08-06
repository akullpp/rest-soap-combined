package de.akull.geoloc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GeolocApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocApplication.class, args);
    }
}
