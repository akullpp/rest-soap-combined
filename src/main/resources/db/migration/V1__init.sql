CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE geolocation
(
    count     BIGINT GENERATED ALWAYS AS IDENTITY,
    query     VARCHAR(255) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    longitude VARCHAR(255) NOT NULL,
    latitude  VARCHAR(255) NOT NULL,
    PRIMARY KEY (query, address)
);
