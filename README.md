# Geoloc

This is a example project which functions as a gateway to Google's Geolocation API to show how SOAP and REST can co-exist side-by-side.

## Usage

### Environment variables

The following environment variables are expected:

```sh
GEOLOC_POSTGRES_USER=${POSTGRES_USER}
GEOLOC_POSTGRES_PASSWORD=${POSTGRES_PASSWORD}
GOOGLE_GEOLOCATION_API=${GOOGLE_GEOLOCATION_API_URL}
GOOGLE_GEOLOCATION_KEY=${GOOGLE_GEOLOCATION_API_KEY}
```

Additionally you can run the `dev` profile with:

```sh
GEOLOC_ACTIVE_PROFILES=default,dev
```

which sets the default API url to `https://maps.googleapis.com/maps/api/geocode` and expects a PostgreSQL at `localhost:5432` with the credentials of `postgres/postgres`.

### Maven

```sh
mvn clean install
java -jar target/geoloc-*.jar
```

### Docker

```sh
docker-compose up -d
```
