package de.akull.geoloc.client.google;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import de.akull.geoloc.client.GeolocationResult;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GoogleGeolocationDeserializer extends JsonDeserializer {

    @Override
    public GoogleGeolocationResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode tree = parser.readValueAsTree();

        Iterable<JsonNode> resultNodes = () -> tree.get("results").elements();
        List<GeolocationResult> results = StreamSupport.stream(resultNodes.spliterator(), false)
            .map(resultNode -> GoogleGeolocationResult.builder()
                .address(resultNode.get("formatted_address").asText())
                .latitude(resultNode.get("geometry").get("location").get("lat").asText())
                .longitude(resultNode.get("geometry").get("location").get("lng").asText())
                .build()
            )
            .collect(Collectors.toList());

        return new GoogleGeolocationResponse(results);
    }
}